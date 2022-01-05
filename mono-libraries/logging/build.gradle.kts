plugins {
    id("library-android-module")
}

dependencies {
    implementation("androidx.core:core-ktx:1.6.0")
    val composeVersion = rootProject.extra["compose_version"]
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")

    testImplementation("junit:junit:latest.release")
}
