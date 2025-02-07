plugins {
    id(Libs.plugin.library)
    id(Libs.plugin.kotlin_serialization)
}

android("ru.itis.liiceberg.common")

dependencies {
    implementation(*Libs.bundle.navigation)
    implementation(*Libs.bundle.serializationJson)
    implementation(*Libs.bundle.splashScreen)
    implementation(*Libs.bundle.firebase_common)
}
repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}