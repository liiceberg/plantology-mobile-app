plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.myplants_api")

dependencies {
    implementation(projects.coreCommon)
}

