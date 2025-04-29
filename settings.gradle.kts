rootProject.name = "monomial"

include("monomial-api", "monomial-provider-fabric")

pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        gradlePluginPortal()
    }
}
