import com.android.build.api.dsl.ApplicationExtension

plugins {
    alias(libs.plugins.agp.app)
    alias(libs.plugins.hiddenapi.refine)
    alias(libs.plugins.ksp)
    alias(libs.plugins.aproc)
}

val cfgMinSdkVersion: Int by rootProject.extra
val cfgTargetSdkVersion: Int by rootProject.extra
val cfgCompileSdkVersion: Int by rootProject.extra
val cfgSourceCompatibility: JavaVersion by rootProject.extra
val cfgTargetCompatibility: JavaVersion by rootProject.extra

configure<ApplicationExtension> {
    namespace = "xyz.mufanc.ncm"
    compileSdk = cfgCompileSdkVersion

    defaultConfig {
        applicationId = "xyz.mufanc.ncm"
        minSdk = cfgMinSdkVersion
        targetSdk = cfgTargetSdkVersion
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles("proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = cfgSourceCompatibility
        targetCompatibility = cfgTargetCompatibility
    }
}

dependencies {
    compileOnly(project(":hiddenapi"))
    implementation(libs.hiddenapi.runtime)
}
