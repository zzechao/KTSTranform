pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
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
include(":buildModule")
include(":gradleTest")

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