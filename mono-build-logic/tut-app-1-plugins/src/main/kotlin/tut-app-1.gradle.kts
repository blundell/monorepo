import com.blundell.SemVersion

plugins {
    id("app-android-module")
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        applicationId = "com.blundell.tut1"

        SemVersion(0, 1, 0, 0).apply {
            versionCode = asCode()
            versionName = asName()
        }
    }
}
