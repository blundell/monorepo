plugins {
    `kotlin-dsl` // This enables src/main/kotlin
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    implementation("com.android.tools.build:gradle:7.0.4")
    implementation(project(":android-k-plugins"))
}
