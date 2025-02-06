plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.myplants_api")

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)

    testImplementation(*Libs.bundle.unitTests)
}