plugins {
    id(Libs.plugin.library)
    id(Libs.plugin.kotlin_serialization)
}

android("ru.itis.liiceberg.common")

dependencies {
    implementation(*Libs.bundle.network)
    implementation(*Libs.bundle.firebase)
    implementation(*Libs.bundle.navigation)
    implementation(*Libs.bundle.serializationJson)
    implementation(*Libs.bundle.splashScreen)

    testImplementation(*Libs.bundle.unitTests)
}
repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}