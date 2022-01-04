package id.walt.vclib.registry

import id.walt.vclib.credentials.*
import id.walt.vclib.model.VerifiableCredential
//ANDROID PORT
//import org.lighthousegames.logging.logging
//ANDROID PORT
import kotlin.reflect.KClass

class CredentialTypeAlreadyRegisteredException(registration: VcTypeRegistry.TypeRegistration) :
    IllegalArgumentException("Type \"${registration.vc.simpleName!!}\" [${registration.metadata.type.joinToString()}] already exists in the registry.")

object VcTypeRegistry {
    //ANDROID PORT
    //private val log = logging()
    //ANDROID PORT
    private val registry = HashMap<Int, TypeRegistration>()

    init {
        //ANDROID PORT
        //log.info { "Registering default templates" }
        //ANDROID PORT
        register<PermanentResidentCard>(PermanentResidentCard)
        register<VerifiableAttestation>(VerifiableAttestation)
        register<VerifiableAuthorization>(VerifiableAuthorization)
        register<VerifiablePresentation>(VerifiablePresentation)
        register<Europass>(Europass)
        register<UniversityDegree>(UniversityDegree)
        register<VerifiableId>(VerifiableId)
        register<VerifiableDiploma>(VerifiableDiploma)
        register<GaiaxCredential>(GaiaxCredential)
        register<GaiaxSelfDescription>(GaiaxSelfDescription)
        register<GaiaxServiceOffering>(GaiaxServiceOffering)
        register<VerifiableVaccinationCertificate>(VerifiableVaccinationCertificate)
        register<ProofOfResidence>(ProofOfResidence)
        register<ParticipantCredential>(ParticipantCredential)
    }

    class TypeRegistration(
        val vc: KClass<out VerifiableCredential>,
        val metadata: VerifiableCredentialMetadata,
        val isPrimary: Boolean = true
    )

    private fun computeKey(type: List<String>) = type.sorted().hashCode()

    fun remove(type: List<String>) = registry.remove(computeKey(type))

    fun contains(type: List<String>) = registry.contains(computeKey(type))
    fun contains(type: String) = getRegistration(type) != null


    private fun registerDefinition(
        type: List<String>,
        registration: TypeRegistration
    ) {
        if (contains(type)) {
            throw CredentialTypeAlreadyRegisteredException(registration)
        }

        registry[computeKey(type)] = registration
    }

    fun register(metadata: VerifiableCredentialMetadata, vc: KClass<out VerifiableCredential>) {
        //ANDROID PORT
        //log.info { "Registering ${vc.simpleName}..." }
        //ANDROID PORT

        registerDefinition(metadata.type, TypeRegistration(vc, metadata))

        metadata.aliases.forEach {
            registerDefinition(it, TypeRegistration(vc, metadata, false))
        }
    }

    fun getRegistration(type: String) = registry.values.firstOrNull { it.vc.simpleName == type && it.isPrimary }

    fun getType(type: List<String>): KClass<out VerifiableCredential>? = registry[computeKey(type)]?.vc
    fun getType(type: String): KClass<out VerifiableCredential>? = getRegistration(type)?.vc
    fun getMetadata(type: List<String>): VerifiableCredentialMetadata = registry[computeKey(type)]!!.metadata
    fun getMetadata(type: String): VerifiableCredentialMetadata = getRegistration(type)!!.metadata

    fun getTypesWithTemplate() = registry.filterValues { it.metadata.template != null }
    fun getTemplateTypes() = getTypesWithTemplate().map { it.value.vc.simpleName!! }

    inline fun <reified T : VerifiableCredential> register(metadata: VerifiableCredentialMetadata) = register(metadata, T::class)
}
