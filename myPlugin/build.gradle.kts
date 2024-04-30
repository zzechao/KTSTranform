plugins {
    `kotlin-dsl`
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
}

gradlePlugin {
    plugins {
        register("TestPlugin") {
            id = "com.zhouz.test.myTestPlugin"
            implementationClass = "com.zhouz.test.TestPlugin"
        }
    }
}

group = "com.zhouz.plugin"
version = "1.0.0"

publishing {
    publications.create<MavenPublication>("myPlugin") {
        groupId = "com.zhouz.plugin"
        version = "1.0.0"
    }
    repositories {
        maven(uri("../maven-repo/snapshot"))
    }
}