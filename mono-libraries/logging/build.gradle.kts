plugins {
    id("library-android-module")
}

dependencies {
    val implementation by configurations
    val testImplementation by configurations

    implementation("androidx.core:core-ktx:1.7.0")
    val composeVersion = rootProject.extra["compose_version"]
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")

    testImplementation("junit:junit:latest.release")
}
