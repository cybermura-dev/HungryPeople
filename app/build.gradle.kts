plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "ru.takeshiko.hungrypeople"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.takeshiko.hungrypeople"
        minSdk = 26
        targetSdk = 35
        versionCode = 2
        versionName = "1.0"

        // testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {

    // App dependencies
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.viewpager2)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.dotsindicator)

    // Supabase dependencies
    implementation(platform(libs.bom))
    implementation(libs.postgrest.kt)

    // Networking
    implementation(libs.ktor.client.android)

    // SDK Yandex Map Kit
    implementation(libs.maps.mobile)

    // Testing dependencies
    // testImplementation("junit:junit:4.13.2")
    // androidTestImplementation("androidx.test.ext:junit:1.2.1")
    // androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}