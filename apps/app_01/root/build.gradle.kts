plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.dkronig.launcher"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dkronig.root"
        minSdk = 35
        targetSdk = 35
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

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(project(":common"))
    implementation(project(":maswe_crypto"))
    implementation(project(":maswe_platform"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

// -------------------------
// Automatically install other apps when running launcher
// -------------------------
android.applicationVariants.all { variant ->
    if (variant.name.equalsIgnoreCase("debug")) {
        variant.installProvider.dependsOn(
            project(":maswe_storage").tasks.named("installDebug")
        )
    }
}
