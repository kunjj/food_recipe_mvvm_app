plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.foodrecipesapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodrecipesapplication"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //Dagger-Hilt.
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")

    // Navigation Components.
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.5.0")

    // Room Dependency.
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    //Coil Dependency.
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Lottie Animation Dependency.
    implementation("com.airbnb.android:lottie:6.0.0")

    //Shimmmer Effect For Recycler view.
    implementation("com.facebook.shimmer:shimmer:0.5.0")
    implementation("com.todkars:shimmer-recyclerview:0.4.1")

    implementation("org.jsoup:jsoup:1.10.3")

    implementation("io.ak1:bubbletabbar:1.0.8")
}