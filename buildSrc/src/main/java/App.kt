object App {

    const val minSdk = 26
    const val targetSdk = 34
    const val compileSdk = 34
    const val packageName = "ru.itis.liiceberg.app"

    val code
        get() = version

    val name
        get() = "$major.$minor.$patch"

    private var version = 1

    private var major = 0
    private var minor = 0
    private var patch = 1
}