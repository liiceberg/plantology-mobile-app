plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.ui")

dependencies {
    implementation(projects.coreCommon)

    implementation(*Libs.bundle.googleFonts)
    implementation(*Libs.bundle.coil)
}