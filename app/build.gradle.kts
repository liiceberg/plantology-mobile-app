plugins {
    id(Libs.plugin.application)

}

android("ru.itis.liiceberg.app")

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.featureAuth)

    implementation(*Libs.bundle.navigation)
    implementation(*Libs.bundle.splashScreen)
    implementation(*Libs.bundle.network)
    implementation(*Libs.bundle.firebase)
}
