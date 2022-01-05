import com.blundell.systemPropertyIsInAppPurchaseTest

plugins {
    id("android-module")
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
            if (systemPropertyIsInAppPurchaseTest()) {
                println(" IAP TEST - DEBUG BUILD WITH PROD PACKAGE NAME -")
            } else {
                applicationIdSuffix = ".debug"
            }
        }
    }
}
