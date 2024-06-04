import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.bouncycastle.util.Properties
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

val properties = Properties().apply{
    load(FileInputStream(rootProject.file("local.properties")))
}

android {
    namespace = "kr.ac.tukorea.whereareu"
    compileSdk = 34

    defaultConfig {
        applicationId = "kr.ac.tukorea.whereareu"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "NAVER_CLIENT_ID", "\""+properties["NAVER_CLIENT_ID"]+"\"")
        buildConfigField("String", "NAVER_CLIENT_SECRET", "\""+properties["NAVER_CLIENT_SECRET"]+"\"")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            manifestPlaceholders["NAVER_CLIENT_ID"] = properties["NAVER_CLIENT_ID"] as String
        }
        release {
            isMinifyEnabled = false
            manifestPlaceholders["NAVER_CLIENT_ID"] = properties["NAVER_CLIENT_ID"] as String
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures{
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //gson
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation ("com.google.code.gson:gson:2.10.1")


    //coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime:2.6.2")
    implementation ("androidx.activity:activity-ktx:1.9.0")
    implementation ("androidx.fragment:fragment-ktx:1.6.2")


    //lifecycle
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")

    // https://github.com/square/okhttp
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")

    // https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    //CardView
    implementation ("androidx.cardview:cardview:1.0.0")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")

    implementation ("com.google.dagger:hilt-android:2.47")
    kapt ("com.google.dagger:hilt-compiler:2.47")

    //naver map
    implementation("com.naver.maps:map-sdk:3.17.0")

    //위치 추적
    implementation("com.google.android.gms:play-services-location:21.2.0")

    //로딩화면
    implementation ("com.airbnb.android:lottie:5.0.2")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.14.2")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.2")

    implementation ("com.github.prolificinteractive:material-calendarview:2.0.1")
    implementation ("com.jakewharton.threetenabp:threetenabp:1.2.1")
}
kapt {
    correctErrorTypes = true
}
