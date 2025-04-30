plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.schedule_impl")

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.featureScheduleApi)

    testImplementation(*Libs.bundle.unitTests)
}