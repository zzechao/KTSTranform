plugins {
    `java-gradle-plugin`
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(gradleKotlinDsl())
    compileOnly("com.android.tools.build:gradle-api:8.0.2")
    compileOnly("org.ow2.asm:asm-commons:9.6")
    compileOnly("org.ow2.asm:asm-tree:9.6")
}