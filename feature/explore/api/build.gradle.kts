plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.explore_api")

dependencies {
    testImplementation(*Libs.bundle.unitTests)
}