plugins {
    id("java-library")
    id("groovy")
    id("org.jetbrains.kotlin.jvm")
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
        classpath("org.apache.commons:commons-compress:1.21")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(localGroovy())
    implementation(gradleApi())
    implementation(gradleKotlinDsl())

    implementation("com.android.tools.build:gradle:7.0.4")
    implementation("com.android.tools.build:gradle-api:7.0.4")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin-api:1.9.0")
    implementation("org.ow2.asm:asm-commons:9.3")
    implementation("commons-io:commons-io:2.7")
}
