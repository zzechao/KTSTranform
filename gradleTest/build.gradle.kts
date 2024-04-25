import org.gradle.api.internal.project.ProjectStateInternal

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}


android {
    namespace = "com.zhouz.gradletest"
    compileSdk = 34

    defaultConfig {
        minSdk = 16
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
}


// 任务
task("name") {
    println("2222")
}.doFirst {
    println("doFirst")
}.doLast {
    println("doLast")
}

// 自定义任务创建
@CacheableTask
open class Test : DefaultTask() {

    @TaskAction
    fun action1() {
        println("action1")
    }

    @TaskAction
    fun action2() {
        println("action2")
    }
}

val task = tasks.create("DefaultTask", Test::class)
task.doFirst {
    println("doFirst")
}.doLast {
    println("doLast")
}

// 任务依赖
task("lib1") {
    doLast {
        println("lib1")
    }
}

task("lib2") {
    doLast {
        println("lib2")
    }
}

task("lib3") {
    doLast {
        println("lib3")
    }
    dependsOn(tasks.find { it.name == "lib1" }, tasks.find { it.name == "lib2" })
}

// 任务排序
val taskA = tasks.register("taskA") {
    doLast {
        println("taskA")
    }
}

val taskB = tasks.register("taskB") {
    doLast {
        println("taskB")
    }
}

taskB.configure {
    this.mustRunAfter(taskA)
    dependsOn(taskA)
}


val project = findProject(":app")
//project?.afterEvaluate {
//    println("app afterEvaluate")
//}