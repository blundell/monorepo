pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
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

rootProject.name = "tut-app-1"

include("app")
monoInclude("http")
monoInclude("logging")

fun monoInclude(name: String) {
    include(":$name")
    project(":$name").projectDir = File("../mono-libraries/$name")
}
