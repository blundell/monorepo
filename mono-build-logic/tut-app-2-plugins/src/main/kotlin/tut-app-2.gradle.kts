import com.blundell.*

plugins {
    id("app-android-module")
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        applicationId = "com.blundell.tut2"

        SemVersion(0, 1, 3, 0).apply {
            versionCode = asCode()
            versionName = asName()
        }
    }
}
