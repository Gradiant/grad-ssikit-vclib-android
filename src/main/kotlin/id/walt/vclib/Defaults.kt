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
        VcLibManager.register<PermanentResidentCard>(PermanentResidentCard)
        VcLibManager.register<VerifiableAttestation>(VerifiableAttestation)
        VcLibManager.register<VerifiableAuthorization>(VerifiableAuthorization)
        VcLibManager.register<VerifiablePresentation>(VerifiablePresentation)
        VcLibManager.register<Europass>(Europass)
        VcLibManager.register<UniversityDegree>(UniversityDegree)
        VcLibManager.register<VerifiableId>(VerifiableId)
        VcLibManager.register<VerifiableDiploma>(VerifiableDiploma)
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
