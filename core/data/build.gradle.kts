plugins {
    id(Libs.plugin.library)
    id(Libs.plugin.google_services)
    id(Libs.plugin.crashlytics)
}

android("ru.itis.liiceberg.data")

dependencies {
    implementation(projects.coreCommon)
    implementation(*Libs.bundle.network)
    implementation(*Libs.bundle.room)
    implementation(*Libs.bundle.firebase_auth)
    implementation(*Libs.bundle.firebase_db)

    testImplementation(*Libs.bundle.unitTests)
}