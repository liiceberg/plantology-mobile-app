plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.data")

dependencies {
    implementation(projects.coreCommon)
    implementation(*Libs.bundle.network)
    implementation(*Libs.bundle.room)

    testImplementation(*Libs.bundle.unitTests)
}