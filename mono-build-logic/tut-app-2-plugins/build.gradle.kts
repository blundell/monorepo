plugins {
//    id("groovy-gradle-plugin") // This enables src/main/groovy
    `kotlin-dsl` // This enables src/main/kotlin
//    id("java-gradle-plugin")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30") // 1.4.10
    implementation("com.android.tools.build:gradle:7.0.4")  //4.1.0
    implementation(project(":android-k-plugins"))
}
