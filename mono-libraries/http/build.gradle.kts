plugins {
    id("library-android-module")
}

dependencies {
    implementation(project(":logging"))

    implementation("androidx.core:core-ktx:1.6.0")
    val composeVersion = rootProject.extra["compose_version"]
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")

    testImplementation("junit:junit:latest.release")
}
