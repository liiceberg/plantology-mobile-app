plugins {
    id(Libs.plugin.gradleVersionsPlugin) version (Libs.gradleVersionsPluginVersion)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
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