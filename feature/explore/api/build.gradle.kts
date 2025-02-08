plugins {
    id(Libs.plugin.library)
}

android("ru.itis.liiceberg.explore_api")

dependencies {
    testImplementation(*Libs.bundle.unitTests)
//    testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0"
//    testImplementation "io.mockk:mockk:1.12.0"
}