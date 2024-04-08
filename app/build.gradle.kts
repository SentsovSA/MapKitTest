import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

val mapkitApiKey by extra { getMapKitApiKey() }

fun getMapKitApiKey(): String {
    val properties = Properties()
    project.file("C:/Users/sents/AndroidStudioProjects/MapKitTest/local.properties")
        .inputStream().use { properties.load(it) }
    val value = properties.getProperty("MAPKIT_API_KEY", "")
    if (value.isEmpty()) {
        throw InvalidUserDataException("MapKit API key is not provided. Set your API key in the project's local.properties file: `MAPKIT_API_KEY=<your-api-key-value>`.")
    }
    return value
}


android {
    namespace = "com.example.mapkittest"
    compileSdk = 33

    buildFeatures{
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.mapkittest"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "MAPKIT_API_KEY", "\"${mapkitApiKey}\"")
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

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.yandex.android:maps.mobile:4.3.1-full")
    implementation ("com.microsoft.appcenter:appcenter-analytics:5.0.4")
    implementation ("com.microsoft.appcenter:appcenter-crashes:5.0.4")
    implementation ("com.microsoft.appcenter:appcenter-distribute:5.0.4")
}
