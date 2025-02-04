plugins {
    id(Libs.plugin.application)
    id(Libs.plugin.google_services)
    id(Libs.plugin.crashlytics)
}

android("ru.itis.liiceberg.app")

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.featureAuthImpl)
    implementation(projects.featureExploreImpl)
    implementation(projects.featureMyplantsImpl)
    implementation(projects.featureSettingsImpl)

    implementation(*Libs.bundle.navigation)
    implementation(*Libs.bundle.splashScreen)
    implementation(*Libs.bundle.network)
    implementation(*Libs.bundle.firebase_auth)
    implementation(*Libs.bundle.coil)
}
