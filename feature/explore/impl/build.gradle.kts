plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.explore_impl")

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.featureExploreApi)

    testImplementation(*Libs.bundle.unitTests)
}