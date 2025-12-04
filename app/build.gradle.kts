plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "cu.xetid.examplemvi"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "cu.xetid.examplemvi"
        minSdk = 24
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
            // Le decimos a la build 'release' que use la firma
            signingConfig = signingConfigs.getByName("debug") // Usamos debug como base, pero las variables de entorno lo sobreescribirán

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true // Habilita View Binding
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
    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
//Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
//Gson converter json to model
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
//viewmodels
    implementation("androidx.fragment:fragment-ktx:1.8.9")
//RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.4.0")
}