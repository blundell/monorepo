plugins {
    `kotlin-dsl` // This enables src/main/kotlin
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    implementation("com.android.tools.build:gradle:7.0.4")
    api(project(":android-plugins"))
}
