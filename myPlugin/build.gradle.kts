plugins {
    `kotlin-dsl`
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

group = "com.zhouz.asm.myPlugin"

version = "1.0.0"

dependencies {
    implementation(gradleApi())
}

configure<PublishingExtension> {
    publications.create<MavenPublication>("myPlugin") {
        groupId = "com.zhouz.asm.myPlugin"
        artifactId = "MyPlugin"
        version = "1.0.0"
        pom.packaging = "jar"
    }
}