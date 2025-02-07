plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.settings_impl")

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.featureSettingsApi)
}