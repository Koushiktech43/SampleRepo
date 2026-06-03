plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.android.sampletestapplication"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.android.sampletestapplication"
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

// KtLint configuration - downloads and runs ktlint automatically
configurations.register("ktlint")

dependencies {
    "ktlint"(libs.ktlint)
}

val ktlintRuntime = configurations["ktlint"]

tasks.register<JavaExec>("ktlintCheck") {
    description = "Check Kotlin code with ktlint"
    group = "verification"
    classpath = ktlintRuntime
    mainClass.set("com.pinterest.ktlint.Main")
    workingDir = rootProject.rootDir
    args(
        "app/src/main/java"
    )
}

tasks.register<JavaExec>("ktlintFormat") {
    description = "Format Kotlin code with ktlint (auto-fix)"
    group = "formatting"
    classpath = ktlintRuntime
    mainClass.set("com.pinterest.ktlint.Main")
    workingDir = rootProject.rootDir
    args(
        "-F",
        "app/src/main/java"
    )
}
