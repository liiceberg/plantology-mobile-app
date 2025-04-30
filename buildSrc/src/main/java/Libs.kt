object Libs {
    private const val gradleVersion = "8.7.3"
    private const val kotlinVersion = "2.0.0"

    private const val supportVersion = "1.6.0"
    private const val architectureComponentVersion = "2.5.0"

    private const val materialVersion = "1.3.0"
    private const val composeUiVersion = "1.7.3"

    private const val lifecycleVersion = "2.8.4"
    private const val navigationVersion = "2.7.7"

    private const val okHttpVersion = "4.12.0"
    private const val retrofitVersion = "2.9.0"

    private const val kotlinterVersion = "4.2.0"
    private const val rulerVersion = "1.4.0"

    private const val coroutinesVersion = "1.8.0"

    private const val daggerVersion = "2.44.2"

    private const val hiltVersion = "2.51"
    private const val hiltJetpackVersion = "1.2.0"

    private const val firebaseCommonVersion = "21.0.0"
    private const val crashlyticsGradleVersion = "2.9.9"
    private const val crashlyticsVersion = "18.6.2"
    private const val analyticsVersion = "21.5.1"
    private const val messagingVersion = "23.4.1"
    private const val configVersion = "21.6.2"
    private const val firestoreVersion = "25.1.1"
    private const val storageVersion = "21.0.1"
    private const val authVersion = "23.1.0"
    private const val databaseVersion = "21.0.0"
    private const val performanceVersion = "21.0.4"
    private const val performancePluginVersion = "1.4.2"

    private const val coilVersion = "3.0.4"

    private const val splashScreenVersion = "1.0.0"
    private const val serializationVersion = "1.7.1"

    private const val dataStoreVersion = "1.1.2"

    const val navVersion = "2.8.9"

    const val junitVersion = "4.12"
    const val mockitoVersion = "1.13.16"
    const val coroutinesTest = "1.10.1"
    const val mockitoKotlinVersion = "5.1.0"

    const val gradleVersionsPluginVersion = "0.51.0"

    const val kotlinCompilerExtensionVersion = "1.5.15"

    const val composeVersion = "2.0.0"

    const val googleServicesVersion = "4.4.2"

    const val detectVersion = "1.23.7"

    object plugin {
        const val application = "com.android.application"
        const val library = "com.android.library"
        const val java_library = "java-library"
        const val kotlin = "kotlin"
        const val kotlin_kapt = "kotlin-kapt"
        const val kotlin_parcelize = "kotlin-parcelize"
        const val kotlin_serialization = "kotlinx-serialization"
        const val kotlin_android = "kotlin-android"
        const val hilt = "com.google.dagger.hilt.android"
        const val lint = "org.jmailen.kotlinter"
        const val gradleVersionsPlugin = "com.github.ben-manes.versions"
        const val google_services = "com.google.gms.google-services"
        const val compose = "org.jetbrains.kotlin.plugin.compose"
        const val crashlytics = "com.google.firebase.crashlytics"
        const val performance = "com.google.firebase.firebase-perf"
        const val detect = "io.gitlab.arturbosch.detekt"
    }

    object classpath {
        const val android_gradle = "com.android.tools.build:gradle:$gradleVersion"
        const val kotlin_gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val kotlin_serialization = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
        const val crashlytics_gradle =
            "com.google.firebase:firebase-crashlytics-gradle:$crashlyticsGradleVersion"
        const val performance = "com.google.firebase:perf-plugin:$performancePluginVersion"
        const val hilt_gradle = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
        const val navigation_safeArgs_gradle =
            "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
        const val kotlinter = "org.jmailen.gradle:kotlinter-gradle:$kotlinterVersion"
        const val ruler = "com.spotify.ruler:ruler-gradle-plugin:$rulerVersion"
        const val gradleVersionsPlugin =
            "com.github.ben-manes:gradle-versions-plugin:$gradleVersionsPluginVersion"
        const val google_services = "com.google.gms:google-services:$googleServicesVersion"
    }

    object bundle {
        val kotlin = arrayOf("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
        val androidx = arrayOf(
            "androidx.appcompat:appcompat:$supportVersion",
        )
        val compose = arrayOf(
            "androidx.compose.material3:material3:$materialVersion",
            "androidx.compose.ui:ui-tooling-preview:$composeUiVersion",
            "androidx.compose.ui:ui:$composeUiVersion",
            "androidx.compose.material:material-icons-extended",
        )

        val googleFonts = arrayOf("androidx.compose.ui:ui-text-google-fonts:$composeUiVersion")

        val composeUiTooling = arrayOf(
            "androidx.compose.ui:ui-tooling:$composeUiVersion"
        )

        val coroutines = arrayOf(
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion",
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion",
            "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion"
        )

        val dagger = arrayOf("com.google.dagger:dagger:$daggerVersion")
        val daggerKapt = arrayOf("com.google.dagger:dagger-compiler:$daggerVersion")

        val hilt = arrayOf(
            "com.google.dagger:hilt-android:$hiltVersion",
            "androidx.hilt:hilt-navigation-compose:$hiltJetpackVersion"
        )
        val hiltKapt = arrayOf("com.google.dagger:hilt-compiler:$hiltVersion")

        val lifecycle = arrayOf(
            "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion",
            "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion",
            "androidx.lifecycle:lifecycle-process:$lifecycleVersion",
            "androidx.lifecycle:lifecycle-runtime-compose-android:$lifecycleVersion"
        )
        val lifecycleKapt = arrayOf("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")

        val room = arrayOf(
            "androidx.room:room-runtime:$architectureComponentVersion",
            "androidx.room:room-rxjava2:$architectureComponentVersion",
            "androidx.room:room-ktx:$architectureComponentVersion"
        )
        val roomKapt = arrayOf("androidx.room:room-compiler:$architectureComponentVersion")

        val navigation = arrayOf(
            "androidx.navigation:navigation-compose:$navVersion"
        )

        val firebase_common = arrayOf("com.google.firebase:firebase-common-ktx:$firebaseCommonVersion")

        val firebase = arrayOf(
            "com.google.firebase:firebase-crashlytics-ktx:$crashlyticsVersion",
            "com.google.firebase:firebase-analytics-ktx:$analyticsVersion",
            "com.google.firebase:firebase-messaging-ktx:$messagingVersion",
            "com.google.firebase:firebase-config-ktx:$configVersion",
            "com.google.firebase:firebase-perf-ktx:$performanceVersion"
        )

        val firebase_auth = arrayOf(
            "com.google.firebase:firebase-auth-ktx:$authVersion",
            "com.google.firebase:firebase-storage:$storageVersion",
            "com.google.firebase:firebase-firestore:$firestoreVersion",
            "com.google.firebase:firebase-database-ktx:$databaseVersion"
        )

        val network = arrayOf(
            "com.squareup.okhttp3:okhttp:$okHttpVersion",
            "com.squareup.okhttp3:logging-interceptor:$okHttpVersion",
            "com.squareup.retrofit2:retrofit:$retrofitVersion",
            "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        )

        val splashScreen = arrayOf("androidx.core:core-splashscreen:$splashScreenVersion")

        val dataStore = arrayOf("androidx.datastore:datastore-preferences:$dataStoreVersion")

        val serializationJson =
            arrayOf("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

        const val unitTestsRunner = "android.support.test.runner.AndroidJUnitRunner"

        val unitTests = arrayOf(
            "junit:junit:$junitVersion",
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTest",
            "org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion",
            "io.mockk:mockk:$mockitoVersion"
        )

        val coil = arrayOf(
            "io.coil-kt.coil3:coil-compose:$coilVersion",
            "io.coil-kt.coil3:coil-network-okhttp:$coilVersion"
        )
    }
}