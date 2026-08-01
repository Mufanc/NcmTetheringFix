plugins {
    alias(libs.plugins.agp.lib)
}

val cfgMinSdkVersion: Int by rootProject.extra
val cfgCompileSdkVersion: Int by rootProject.extra
val cfgSourceCompatibility: JavaVersion by rootProject.extra
val cfgTargetCompatibility: JavaVersion by rootProject.extra

android {
    namespace = "xyz.mufanc.ncm.hiddenapi"
    compileSdk = cfgCompileSdkVersion

    defaultConfig {
        minSdk = cfgMinSdkVersion
    }

    compileOptions {
        sourceCompatibility = cfgSourceCompatibility
        targetCompatibility = cfgTargetCompatibility
    }
}

dependencies {
    compileOnly(libs.hiddenapi.annotation)
    annotationProcessor(libs.hiddenapi.processor)
}
