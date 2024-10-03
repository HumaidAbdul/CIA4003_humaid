plugins {
    id("com.android.application") version "8.1.0"
}

dependencies {
    implementation("com.google.android.material:material:1.9.0")  // Make sure this line is added
}


android {
    namespace = "com.example.pjaapp"  // Add this line
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pjaapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
