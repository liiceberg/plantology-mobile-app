plugins {
    id(Libs.plugin.gradleVersionsPlugin) version (Libs.gradleVersionsPluginVersion)
    id(Libs.plugin.compose) version (Libs.composeVersion)
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Libs.classpath.android_gradle)
        classpath(Libs.classpath.kotlin_gradle)
        classpath(Libs.classpath.kotlinter)
        classpath(Libs.classpath.hilt_gradle)
        classpath(Libs.classpath.kotlin_serialization)
        classpath(Libs.classpath.google_services)
        classpath(Libs.classpath.crashlytics_gradle)
        classpath(Libs.classpath.performance)

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}