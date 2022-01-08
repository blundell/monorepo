pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    includeBuild("../mono-build-logic")
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "tut-app-2"

include("app")
monoInclude("http")
monoInclude("logging")

fun monoInclude(name: String) {
    include(":$name")
    project(":$name").projectDir = File("../mono-libraries/$name")
}
