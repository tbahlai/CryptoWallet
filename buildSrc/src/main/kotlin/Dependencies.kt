object ProjectCompileConfig {
    const val compileSdkVersion = 33
    const val buildToolsVersion = "31.0.0"
    const val minSdkVersion = 21
    const val targetSdkVersion = 33
    const val versionCode = 1
    const val defaultVersionName = "0.0.0"
    const val applicationId = "com.tbahlai.cryptowallet"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Third {
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    const val glideOkHttp = "com.github.bumptech.glide:okhttp3-integration:${Versions.glideVersion}"
    const val glideCompose = "com.github.bumptech.glide:compose:${Versions.glideComposeVersion}"

    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2Version}"
    const val converterScalars = "com.squareup.retrofit2:converter-scalars:${Versions.retrofit2Version}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2Version}"
    const val desugaring = "com.android.tools:desugar_jdk_libs:1.0.10"

    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"

    const val viewBindingPropertyDelegate = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.6"
}

object Debug {
    const val timberLogs = "com.jakewharton.timber:timber:5.0.1"
}

object Google {
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx:${Versions.firebaseCrashlyticsVersion}"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx:${Versions.firebaseAnalyticsVersion}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltVersion}"

    const val dataStorePreferencesCore = "androidx.datastore:datastore-preferences-core:${Versions.dataStoreVersion}"
    const val dataStorePreferences = "androidx.datastore:datastore-preferences:${Versions.dataStoreVersion}"

    const val composeUi = "androidx.compose.ui:ui:${Versions.composeVersion}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.composeMaterialVersion}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivityVersion}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
    const val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayoutVersion}"

    const val accompanistSwipeRefresh = "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanistSwipeRefreshVersion}"
    const val accompanistSystemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanistSystemUiControllerVersion}"
}

object AndroidX {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompatVersion}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardviewVersion}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacySupportV4Version}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
    const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.lifecycleVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val securityCrypto = "androidx.security:security-crypto:${Versions.securityVersion}"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtsVersion}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtsVersion}"
    const val viewPager  = "androidx.viewpager2:viewpager2:${Versions.viewPagerVersion}"
}

object Testing {
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.7"
    const val bugfender = "com.bugfender.sdk:android:3.+"
}

object Versions {
    //AndroidX
    const val appcompatVersion = "1.3.0-rc01"
    const val coreKtxVersion = "1.7.0"
    const val cardviewVersion = "1.0.0"
    const val legacySupportV4Version = "1.0.0"
    const val constraintLayoutVersion = "2.1.0-beta01"
    const val lifecycleVersion = "2.4.0"
    const val securityVersion = "1.1.0-alpha01"
    const val navigationVersion = "2.4.1"
    const val fragmentKtsVersion = "1.3.4"
    const val activityKtsVersion = "1.3.0-alpha05"
    const val viewPagerVersion = "1.0.0"

    //Third
    const val materialVersion = "1.5.0"
    const val glideVersion= "4.11.0"
    const val glideComposeVersion= "1.0.0-alpha.1"
    const val calendarViewVersion = "1.0.2"

    //Google
    const val okhttpVersion = "4.9.3"
    const val retrofit2Version = "2.9.0"
    const val gsonVersion = "2.8.6"
    const val firebaseBom = "30.3.1"
    const val hiltVersion = "2.42"
    const val firebaseGradleVersion = "4.3.13"
    const val firebaseAppDistributionVersion = "3.0.1"
    const val firebaseCrashlyticsVersion = "18.2.12"
    const val firebaseAnalyticsVersion = "21.1.0"
    const val firebaseCrashlyticsGradleVersion = "2.9.1"
    const val firebaseDistributionGradleVersion = "3.0.3"
    const val gradleVersion = "7.0.0"
    const val kotlinVersion = "1.7.20"
    const val coroutinesVersion = "1.6.0"
    const val dataStoreVersion = "1.0.0"

    const val composeVersion = "1.3.2"
    const val composeMaterialVersion = "1.3.1"
    const val composeRuntimeVersion = "1.3.2"
    const val composeConstraintLayoutVersion = "1.0.1"
    const val composeActivityVersion = "1.4.0"

    const val accompanistSwipeRefreshVersion = "0.23.0"
    const val accompanistSystemUiControllerVersion = "0.28.0"

    const val ktlintVersion = "10.3.0"
}

object SignConfig {
//    const val storeFile = "keystore.jks"
//    const val storePassword = "123123123Aa"
//    const val signingKeyPassword = "123123123Aa"
//    const val signingKeyAlias = "key"
}

object BugfenderConfig {
    const val token = ""
    const val symbolicationToken = ""
}

object Constants {

    object DEV {
        const val BASE_API_URL = "\"https://ms-finance.p.rapidapi.com\""
        const val BUILD_TYPE = "debug"
        const val API_KEY = "\"774ffd8ed4mshb40af7c27d99241p101cafjsn6a23ba84bdf3\""
        const val API_HOST = "\"ms-finance.p.rapidapi.com\""
    }

    object RELEASE {
        const val BASE_API_URL = "\"https://ms-finance.p.rapidapi.com\""
        const val BUILD_TYPE = "release"
        const val API_KEY = "\"774ffd8ed4mshb40af7c27d99241p101cafjsn6a23ba84bdf3\""
        const val API_HOST = "\"ms-finance.p.rapidapi.com\""
    }

    const val BASE_API_URL = "BASE_API_URL"
    const val API_KEY = "API_KEY"
    const val API_HOST = "API_HOST"
    const val LOGGING_ENABLED = "LOGGING_ENABLED"
    const val BUGFENDER_TOKEN = "BUGFENDER_TOKEN"
}
