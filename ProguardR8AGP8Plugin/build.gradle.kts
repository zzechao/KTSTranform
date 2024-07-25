plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("com.gradle.plugin-publish") version "1.2.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

gradlePlugin {
    plugins {
        register("ARouterAGP8Plugin") {
            id = "com.zhouz.plugin.ProguardR8AGP8Plugin"
            implementationClass = "com.zhouz.proguard.ProguardR8DictionaryGeneratorPlugin"
        }
    }
}

group = "com.zhouz.plugin"
version = "1.0.1"

publishing {
    publications {
        create<MavenPublication>("ProguardR8AGP8Plugin") {
            groupId = "com.zhouz.plugin"
            version = "1.0.1"
        }
    }
    repositories {
        maven(uri("$rootDir/repo/"))
    }
}

dependencies {
    implementation(gradleKotlinDsl())
    implementation(gradleApi())
    implementation(Libraries.kotlinx_coroutines_core)
}