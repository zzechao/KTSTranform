plugins {
    `kotlin-dsl`
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
        classpath("org.apache.commons:commons-compress:1.21")
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())

    implementation("com.android.tools.build:gradle:7.0.3")
    implementation("com.android.tools.build:gradle-api:7.0.3")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin-api:1.4.21")
    implementation("org.ow2.asm:asm-commons:9.3")
    implementation("commons-io:commons-io:2.6")
}