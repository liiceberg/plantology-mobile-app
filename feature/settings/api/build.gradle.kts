plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.settings_api")

dependencies {

    testImplementation(*Libs.bundle.unitTests)
}