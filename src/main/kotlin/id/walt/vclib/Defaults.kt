package id.walt.vclib

import id.walt.vclib.vclist.*
//ANDROID PORT
//import org.lighthousegames.logging.logging
//ANDROID PORT

object Defaults {

    //ANDROID PORT
    //private val log = logging()
    //ANDROID PORT

    private val defaults = lazy {
        with(VcLibManager) {
            register<PermanentResidentCard>(PermanentResidentCard)
            register<VerifiableAttestation>(VerifiableAttestation)
            register<VerifiableAuthorization>(VerifiableAuthorization)
            register<VerifiablePresentation>(VerifiablePresentation)
            register<Europass>(Europass)
            register<UniversityDegree>(UniversityDegree)
            register<VerifiableId>(VerifiableId)
            register<VerifiableDiploma>(VerifiableDiploma)
            register<GaiaxCredential>(GaiaxCredential)
        }
    }

    fun loadVcLibDefaults() {
        // Register default types
        if (!defaults.isInitialized()) {
            //ANDROID PORT
            //log.info { "Registering default templates" }
            //ANDROID PORT
            defaults.value
        }
    }
}
