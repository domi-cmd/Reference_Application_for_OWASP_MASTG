pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "apps"
include(":common")
include(":root")
include(":maswe_storage")
include(":maswe_crypto")
include(":maswe_platform")

project(":root").projectDir = file("root")
project(":common").projectDir = file("common")
project(":maswe_crypto").projectDir = file("maswe_crypto")
project(":maswe_platform").projectDir = file("maswe_platform")
project(":maswe_storage").projectDir = file("maswe_storage")