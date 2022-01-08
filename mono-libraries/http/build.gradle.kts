plugins {
    id("library-android-module")
}

dependencies {
    val implementation by configurations
    val testImplementation by configurations

    implementation(project(":logging"))

    implementation("androidx.core:core-ktx:1.6.0")
    val composeVersion = rootProject.extra["compose_version"]
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")

    testImplementation("junit:junit:latest.release")
}
