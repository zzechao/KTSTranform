plugins {
    `kotlin-dsl`
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

//tasks.register("clean", Delete::class.java) {
//    delete(rootProject.buildDir)
//}

repositories {
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("TestPlugin"){
            id = "test.TestPlugin"
            implementationClass = "com.zhouz.test.TestPlugin"
        }
    }
}


dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
}