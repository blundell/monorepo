package com.blundell

import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.VariantDimension

fun ApplicationDefaultConfig.appIdentifier(): String {
    return "${applicationId}${applicationIdSuffix ?: ""}"
}

fun VariantDimension.buildConfigStringField(key: String, value: String) {
    buildConfigField("String", key, value)
    println("  $key BuildConfig: $value")
}

fun VariantDimension.resStringValue(key: String, value: String) {
    resValue("string", key, value)
    println("  $key ResourceString: $value")
}

fun quotedString(value: String): String {
    return "\"${value}\""
}

fun systemPropertyIsInAppPurchaseTest(): Boolean {
    return System.getProperty("iap-test", "false").toBoolean()
}

/**
 * Cannot access class 'java.io.File'. Check your module classpath for missing or conflicting dependencies
 * ^ avoiding this errors (seems like a gradle bug) by duplicating this method in each settings.gradle.kts
 */
//fun SettingsDelegate.monoInclude(name: String) {
//    include(":$name")
//    val project: ProjectDescriptor = project(":$name")
//    project.projectDir = File("../mono-libraries/$name")
//}
