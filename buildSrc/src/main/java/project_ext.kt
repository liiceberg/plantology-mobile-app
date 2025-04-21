import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.fileTree

private val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension ?: error("Not an Android module: $name")

fun Project.common(namespace: String) {
    plugins.apply {
        apply(Libs.plugin.kotlin_android)
        apply(Libs.plugin.kotlin_kapt)
        apply(Libs.plugin.lint)
        apply(Libs.plugin.hilt)
        apply(Libs.plugin.compose)
    }

    android.apply {
        this.namespace = namespace
        compileSdkVersion(App.compileSdk)

        defaultConfig {
            minSdk = App.minSdk
            targetSdk = App.targetSdk

            testInstrumentationRunner = Libs.bundle.unitTestsRunner
        }

        buildTypes {
            getByName(BuildTypes.debug) {
                isMinifyEnabled = false
            }
            create(BuildTypes.alpha) {
                initWith(getByName(BuildTypes.debug))
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
            getByName(BuildTypes.release) {
                isMinifyEnabled = true
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        composeOptions {
            kotlinCompilerExtensionVersion = Libs.kotlinCompilerExtensionVersion
        }

        buildFeatures.apply {
            compose = true
        }

    }

    dependencies {
        implementation(project.fileTree("include" to "*.jar", "dir" to "libs"))

        implementation(*Libs.bundle.kotlin)
    }
}

fun Project.hilt() {
    dependencies {
        implementation(*Libs.bundle.hilt)
        kapt(*Libs.bundle.hiltKapt)
    }
}

fun Project.coroutines() {
    dependencies {
        implementation(*Libs.bundle.coroutines)
    }
}

fun Project.uiComponents() {
    dependencies {
        implementation(*Libs.bundle.androidx)
        implementation(*Libs.bundle.compose)
        debugImplementation(*Libs.bundle.composeUiTooling)
    }
}

fun Project.lifecycle() {
    dependencies {
        implementation(*Libs.bundle.lifecycle)
        kapt(*Libs.bundle.lifecycleKapt)
    }
}

fun Project.android(namespace: String) {
    common(namespace)
    uiComponents()
    hilt()
    coroutines()
    lifecycle()
}