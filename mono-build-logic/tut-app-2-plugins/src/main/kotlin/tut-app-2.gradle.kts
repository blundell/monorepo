import com.blundell.SemVersion

plugins {
    id("app-android-module")
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
