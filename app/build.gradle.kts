plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("test.TestPlugin")
}

android {
    namespace = "com.zhouz.ktstranform"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zhouz.ktstranform"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(project(":gradleTest"))
}

/** 1、project 生命周期, 如果是当前project只能afterEvaluate
2、子布局project 无法获取 父布局的
afterEvaluate 会报错 Cannot run Project.afterEvaluate(Action)
when the project is already evaluated.
只能获取当前afterEvaluate或者其子布局的beforeEvaluate和afterEvaluate
3、setting中配置才会生效
 */

//val project = findProject(":gradleTest")
//project?.beforeEvaluate {
//    println("gradleTest beforeEvaluate")
//}
//project?.afterEvaluate {
//    println("gradleTest afterEvaluate")
//}