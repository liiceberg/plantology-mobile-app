plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.reminder_api")

dependencies {
    implementation(projects.coreCommon)
}
