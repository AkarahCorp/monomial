rootProject.name = "monomial"

include(
    "monomial-api",
    "monomial-provider-fabric",
    "example-plugin"
)

pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        gradlePluginPortal()
    }
}
