plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ProjectCompileConfig.compileSdkVersion
    buildToolsVersion = ProjectCompileConfig.buildToolsVersion

    defaultConfig {
        applicationId = ProjectCompileConfig.applicationId

        minSdk = ProjectCompileConfig.minSdkVersion
        targetSdk = ProjectCompileConfig.targetSdkVersion
        versionCode = ProjectCompileConfig.versionCode /*getVersionCode(project)*/
        versionName = ProjectCompileConfig.defaultVersionName /*getVersionName(project)*/

        testInstrumentationRunner = ProjectCompileConfig.testInstrumentationRunner
        multiDexEnabled = true

        buildTypes {
            getByName("debug") {
                applicationIdSuffix = ".dev"
                isDebuggable = true
                matchingFallbacks.add("release")

                buildConfigField("String", Constants.BASE_API_URL, Constants.DEV.BASE_API_URL)
                buildConfigField("String", Constants.API_KEY, Constants.DEV.API_KEY)
                buildConfigField("String", Constants.API_HOST, Constants.DEV.API_HOST)
            }

            getByName("release") {
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                buildConfigField("String", Constants.BASE_API_URL, Constants.RELEASE.BASE_API_URL)
                buildConfigField("String", Constants.API_KEY, Constants.RELEASE.API_KEY)
                buildConfigField("String", Constants.API_HOST, Constants.RELEASE.API_HOST)
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        composeOptions {
            kotlinCompilerExtensionVersion = Versions.composeVersion
        }

        kotlinOptions {
            freeCompilerArgs += "-Xuse-experimental=kotlin.contracts.ExperimentalContracts"
            freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
            jvmTarget = JavaVersion.VERSION_11.toString()

        }

        buildFeatures {
            compose = true
            viewBinding = true
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Third.stdlib)
    implementation(Third.coroutines)
    implementation(Google.material)
    implementation(Google.gson)

    implementation(Google.composeUi)
    implementation(Google.composeMaterial)
    implementation(Google.composeToolingPreview)
    implementation(Google.composeActivity)
    implementation(Google.composeConstraintLayout)
    debugImplementation(Google.composeUiTooling)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.securityCrypto) {
        exclude(group = "com.google.protobuf")
    }
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.legacy)
    implementation(AndroidX.lifecycleProcess)
    implementation(AndroidX.cardview)
    implementation(AndroidX.navigationFragmentKtx)
    implementation(AndroidX.navigationUiKtx)
    implementation(AndroidX.activityKtx)
    implementation(AndroidX.fragmentKtx)
    implementation(AndroidX.viewPager)

    implementation(Debug.timberLogs)

    implementation(Third.okhttp)
    implementation(Third.okhttpLogging)
    implementation(Third.retrofit2)
    implementation(Third.converterGson)
    implementation(Third.converterScalars)

    implementation(Google.hiltAndroid)
    kapt(Google.hiltCompiler)

    implementation(Third.glideCompose)
    implementation(Third.viewBindingPropertyDelegate)
    implementation(Google.accompanistSwipeRefresh)
    implementation(Google.accompanistSystemUiController)
}