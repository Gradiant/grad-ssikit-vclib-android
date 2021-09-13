# Changelog

All notable changes to this project will be documented in this file.

## [Unreleased]

- Fix JWT claim named "vp" for verifiable presentation according to EU spec

## [1.4.6] - 2021-09-09

-   JWT-aware credential parsing
-   preserve original credential JSON body and JWT (for JWT credentials)
-   support parsing verifiable presentations with nested VCs in JSON-LD or JWT format

## [1.4.5] - 2021-09-01

-   Changed default data for Verifiable ID, Verifiable Diploma and Europass credential templates

## [1.4.4] - 2021-08-26

## [1.4.4] - 2021-08-26

-   Added credential template for Verifiable Diplomas
-   Updated Europass & Verifiable ID data model in aligned with the EBSI Schemas
-   Improved Schema Generator

## [1.4.3] - 2021-08-19

-   Added VerifiableID model

## [1.4.2] - 2021-08-18

## [1.4.1] - 2021-08-16

## [1.4.0] - 2021-08-14

-   Make TemplateManager usable with template ids
    -   ```kotlin
        VcTemplateManager.loadTemplate("Europass")
        VcTemplateManager.loadTemplate(listOf("VerifiableCredential, VerifiableAttestation, Europass"))
        ```
-   Added template id listing
    -   ```kotlin
        VcTemplateManager.getTemplateList()
        ```
-   Added toMap method for VCs
    -   ```kotlin
        VerifiableCredential.toMap(): Map<String, Any>
        ```

## [1.3.0] - 2021-07-28

-   reworked VC registration to allow for VC metadata, e.g.

    ```kotlin
    // => Registration
    VcLibManager.register<UniversityDegree>(UniversityDegree)

    // OR VcLibManager.register<UniversityDegree>(UniversityDegree.Companion)

    // => Companion object of VC
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "UniversityDegreeCredential"),
    )
    ```
-   moved TemplateManager to Walt.ID VC-Lib (see id.walt.vclib.templates.VcTemplateManager)

## [1.2.0] - 2021-07-28

### Changed

-   All credential now have a common proof
-   further testing

## [1.1.0] - 2021-07-28

### Changed

-   All credential attributes can now be changed dynamically without recreating the credential instance

## [1.0.0] - 2021-07-28

### Added

-   Initial release

[Unreleased]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.6...HEAD

[1.4.6]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.5...1.4.6

[1.4.5]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.4...1.4.5

[1.4.4]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.4...1.4.4

[1.4.4]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.3...1.4.4

[1.4.3]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.2...1.4.3

[1.4.2]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.1...1.4.2

[1.4.1]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.0...1.4.1

[1.4.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.3.0...1.4.0

[1.4.2]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.3.0...1.4.2

[1.3.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.2.0...1.3.0

[1.2.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.1.0...1.2.0

[1.1.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.0.0...1.1.0

[1.0.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.0.0...1.0.0
