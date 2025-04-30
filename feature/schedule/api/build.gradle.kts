plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.schedule_api")

dependencies {
    implementation(projects.coreCommon)
}
