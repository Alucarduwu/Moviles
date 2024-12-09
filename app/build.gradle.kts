plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services") // Para los servicios de Firebase
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // Dependencias de la interfaz de usuario
    implementation(libs.appcompat)            // AppCompat para compatibilidad con ActionBar y Drawer
    implementation(libs.material)             // Material Design Components (NavigationView y otros)
    implementation(libs.activity)             // Para usar Activity en Kotlin
    implementation(libs.constraintlayout)     // ConstraintLayout para interfaces flexibles
    implementation(libs.drawerlayout)         // Necesario para el DrawerLayout

    // Navegación entre fragmentos
    implementation(libs.navigation.fragment)  // Navegación entre fragmentos
    implementation(libs.navigation.ui)        // UI de navegación

    // Dependencias de Firebase (utilizando el Firebase BoM)
    implementation(platform(libs.firebase.bom)) // Para Firebase BoM (Bill of Materials)
    implementation(libs.google.firebase.auth)    // Firebase Authentication
    implementation("com.google.firebase:firebase-firestore")  // Firestore
    implementation(libs.google.firebase.database) // Firebase Realtime Database

    // Dependencias de pruebas
    testImplementation(libs.junit)            // Dependencia para pruebas unitarias
    androidTestImplementation(libs.ext.junit) // Dependencia para pruebas de UI
    androidTestImplementation(libs.espresso.core) // Dependencia para pruebas de UI de Espresso
}
