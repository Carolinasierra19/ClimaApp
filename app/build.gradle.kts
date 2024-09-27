plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

}

android {
    namespace = "com.example.climaapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.climaapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // aca decclaro la API Key
        val weatherApiKey = project.findProperty("WEATHER_API_KEY") as String
        buildConfigField("String", "WEATHER_API_KEY", "\"$weatherApiKey\"")
    }
    buildFeatures{
        buildConfig = true
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
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
  // comentarios para recordar que era cada cosa
    // esto es para Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // esto es para el  ViewModel y LiveData para el patrón MVVM
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    // Coroutines para operaciones asincrónicas
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    // RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.2.1")

    // Glide o Picasso para cargar imágenes (es opcional no hace falta que lo tenga)
    implementation( "com.github.bumptech.glide:glide:4.13.0")
    implementation ("com.squareup.picasso:picasso:2.71828")


}