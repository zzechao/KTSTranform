pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("$rootDir/maven-repo/snapshot")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KTSTranform"
include(":app")
include(":gradleTest")
include(":ARouterPlugin")

gradle.beforeProject {
    println("${this.name} beforeProject")
}

gradle.afterProject {
    println("${this.name} afterProject")
}

gradle.addBuildListener(object : BuildListener {
    override fun settingsEvaluated(settings: Settings) {
        println("settingsEvaluated")
    }

    override fun projectsLoaded(gradle: Gradle) {
        println("projectsLoaded")
    }

    override fun projectsEvaluated(gradle: Gradle) {
        println("projectsEvaluated")
    }

    override fun buildFinished(result: BuildResult) {
        println("buildFinished ${result.action}")
    }
})

gradle.addListener(object : TaskExecutionGraphListener {
    override fun graphPopulated(graph: TaskExecutionGraph) {

        val map = mutableMapOf<String, Long>()

        graph.beforeTask {
            map[this.name] = System.currentTimeMillis()
            println("beforeActions ${this.name} ${Thread.currentThread().name}")
        }

        graph.afterTask {
            println("afterActions ${this.name} dur:${System.currentTimeMillis() - (map[this.name] ?: System.currentTimeMillis())}")
        }
    }
})
include(":myPlugin")
include(":myExtensionTest")


var time: Long = 0
gradle.taskGraph.beforeTask {
    time = System.currentTimeMillis()
}

gradle.taskGraph.afterTask {
    val durTime = System.currentTimeMillis() - time
    println("任务:$name 执行耗时:${durTime}ms")
}
include(":asmTest")
