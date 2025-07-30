plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.recyclerview"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.recyclerview"
        minSdk = 21
        targetSdk = 36
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
    // Pastikan ini adalah satu-satunya dependensi RecyclerView dan CardView
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    implementation("androidx.cardview:cardview:1.0.0")

    // Dependensi Gson untuk serialisasi/deserialisasi JSON
    implementation("com.google.code.gson:gson:2.10.1") // Gunakan versi stabil terbaru

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    // Hapus baris ini jika Anda sudah mendeklarasikan versi secara eksplisit di atas
    // implementation(libs.recyclerview)
    // implementation(libs.cardview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}