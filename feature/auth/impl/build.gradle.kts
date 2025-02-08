plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.auth_impl")

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.featureAuthApi)

    testImplementation(*Libs.bundle.unitTests)
}