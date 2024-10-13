import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.android.tools.build:gradle:8.5.2")
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0")
    implementation("com.squareup:javapoet:1.13.0")
}