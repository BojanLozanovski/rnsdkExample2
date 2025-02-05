plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

group = "com.github.BojanLozanovski"
version = "1.0"

android {
    namespace = "com.example.rnsdkandroid"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // also for the sdk - hidden dependencies
    //-->
    implementation("com.facebook.soloader:soloader:0.9.0+")
    implementation("com.facebook.infer.annotation:infer-annotation:0.18.0")

    // define a BOM and its version
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:okhttp-urlconnection")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
    //NOTE: This resolves "Lcom/facebook/common/logging/FLog"...
    implementation("com.facebook.fresco:fresco:3.6.0")
    //NOTE: This resolves "Lcom/facebook/imagepipeline/backends/okhttp3/OkHttpImagePipelineConfigFactory;"
    implementation("com.facebook.fresco:imagepipeline-okhttp3:3.6.0")

    //Some differences with the build.gradle from the medium
    //0. They use "api", we use "implementation".
    //1. We don't have two implementations that were present on the initial repo:
//    api "com.google.code.findbugs:jsr305:3.0.2"
//    api "com.squareup.okio:okio:1.15.0"
    //2. We have some extra:
    // - swipe refresh thing.
    //<--

    api("com.github.BojanLozanovski:rnsdkExample1:afd7880949")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"]) // You can use components["release"] or components["debug"] as needed

                groupId = "com.github.BojanLozanovski"
                artifactId = "rnsdkExample2"
                version = "1.0"
            }
        }
    }
}