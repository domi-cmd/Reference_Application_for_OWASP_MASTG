plugins {
    id("com.android.dynamic-feature")
}

android {
    namespace = "com.dkronig.maswe_crypto"
    compileSdk = 35

    defaultConfig {
        minSdk = 35
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packaging {
        resources {
            excludes += listOf("META-INF/versions/**")
        }
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("org.bouncycastle:bcprov-jdk18on:1.78.1")
    implementation(project(":common"))
    implementation(project(":root"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}