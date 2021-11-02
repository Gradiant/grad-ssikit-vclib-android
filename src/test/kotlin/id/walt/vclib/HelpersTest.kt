package id.walt.vclib

import com.beust.klaxon.Json
import id.walt.vclib.Helpers.toCredential
import id.walt.vclib.Helpers.toMap
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.vclist.VerifiableDiploma
import id.walt.vclib.vclist.VerifiableId
import id.walt.vclib.vclist.VerifiablePresentation
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File
import java.nio.file.Path

class HelpersTest : StringSpec({
    "test toMap()" {
        val dummyVcMap = DummyCredential().toMap()

        dummyVcMap["@context"] shouldBe listOf("https://www.w3.org/2018/credentials/v1")
        dummyVcMap["type"] shouldBe listOf("VerifiableCredential", "VerifiableAttestation", "DummyCredential")

        dummyVcMap.containsKey("id") shouldBe false
    }

    "test toCredential JWT Proof VerifiableId striped of redundant claims" {
        val vId = File(Path.of("src", "test", "resources", "jwt", "VerifiableId.txt").toUri())
            .readText()
            .toCredential() as VerifiableId

        vId.id shouldBe "identity#verifiableID#93308ff1-c335-43c0-94da-a6863fb4bb9d"
        vId.issuer shouldBe "did:ebsi:zkpVfh2Gr25RBAXjadtD8eg"
        vId.issuanceDate shouldBe "2021-08-20T00:00:00Z"
        vId.validFrom shouldBe "2021-08-20T00:00:00Z"
        vId.expirationDate shouldBe null
        vId.credentialSubject!!.id shouldBe "did:ebsi:zhueMmLMaCc72prdyt9pd4w"
    }

    "test toCredential JWT Proof VerifiableDiploma striped of redundant claims" {
        val vDiploma = File(Path.of("src", "test", "resources", "jwt", "VerifiableDiploma.txt").toUri())
            .readText()
            .toCredential() as VerifiableDiploma

        vDiploma.id shouldBe "education#higherEducation#87ED2F2270E6C41456E94B86B9D9115B4E35BCCAD200A49B846592C14F79C86BV1Fnbllta0NZTnJkR3lDWlRmTDlSRUJEVFZISmNmYzJhUU5sZUJ5Z2FJSHpWbmZZ"
        vDiploma.issuer shouldBe "did:ebsi:zkpVfh2Gr25RBAXjadtD8eg"
        vDiploma.issuanceDate shouldBe "2021-08-20T00:00:00Z"
        vDiploma.validFrom shouldBe "2020-06-01T00:00:00Z"
        vDiploma.expirationDate shouldBe null
        vDiploma.credentialSubject!!.id shouldBe "did:ebsi:zhueMmLMaCc72prdyt9pd4w"
    }

    "test toCredential JWT Proof VerifiablePresentation striped of redundant claims" {
        val vp = File(Path.of("src", "test", "resources", "jwt", "VerifiablePresentation.txt").toUri())
            .readText()
            .toCredential() as VerifiablePresentation

        vp.id shouldBe "urn:uuid:2f2f3a29-156a-4ae5-bb76-972ca0a84b49"
        vp.holder shouldBe "did:ebsi:zhueMmLMaCc72prdyt9pd4w"
    }
}) {
    data class DummyCredential(
        @Json(name = "@context") var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
        @Json(serializeNull = false) override var id: String? = null
    ) : VerifiableCredential(type) {
        companion object :
            VerifiableCredentialMetadata(listOf("VerifiableCredential", "VerifiableAttestation", "DummyCredential"))
    }
}