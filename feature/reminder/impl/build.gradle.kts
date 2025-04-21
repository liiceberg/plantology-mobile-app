plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.reminder_impl")

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.featureReminderApi)

    implementation(*Libs.bundle.firebase_common)

    testImplementation(*Libs.bundle.unitTests)
}