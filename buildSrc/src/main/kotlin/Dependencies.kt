import LibrariesVersions.ANDROID_X_VERSION
import LibrariesVersions.GRADLE_PLUGIN_VERSION
import LibrariesVersions.KOTLIN_COROUTINES
import LibrariesVersions.KOTLIN_VERSION

object LibrariesVersions {
    const val KOTLIN_VERSION = "1.9.21"
    const val ANDROID_X_VERSION = "1.0.0"
    const val GRADLE_PLUGIN_VERSION = "8.1.0"
    const val KOTLIN_COROUTINES = "1.8.1"
}

object BuildScriptPlugins {
    const val android = "com.android.tools.build:gradle:$GRADLE_PLUGIN_VERSION"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
}

object Libraries {
    const val androidX = "androidx.appcompat:appcompat:$ANDROID_X_VERSION"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_VERSION"
    const val kotlinx_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$KOTLIN_COROUTINES"
}