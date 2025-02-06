plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.myplants_impl")

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.featureMyplantsApi)

    testImplementation(*Libs.bundle.unitTests)
}