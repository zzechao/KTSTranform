plugins { `kotlin-dsl` }

buildscript {
    repositories {
        mavenCentral()
        google()
        maven("https://jitpack.io")
        // 配置HMS Core SDK的Maven仓地址。
        maven("https://s01.oss.sonatype.org/service/local/repositories/snapshots/content/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
        classpath("org.apache.commons:commons-compress:1.21")
    }
}

repositories {
    mavenCentral()
    google()
    maven("https://jitpack.io")
    // 配置HMS Core SDK的Maven仓地址。
    maven("https://s01.oss.sonatype.org/service/local/repositories/snapshots/content/")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

configurations.all {
    resolutionStrategy {
        cacheChangingModulesFor(0, TimeUnit.SECONDS)
        cacheDynamicVersionsFor(0, TimeUnit.SECONDS)
    }
}

dependencies {
    implementation(gradleKotlinDsl())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle-api:8.1.4")
//    implementation("com.android.tools.build:gradle:8.1.4")
//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin-api:1.9.0")
    implementation("org.ow2.asm:asm-commons:9.6")
    implementation("org.ow2.asm:asm-tree:9.6")
    implementation("commons-io:commons-io:2.13.0")
}