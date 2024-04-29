package com.zhouz.test

import org.gradle.api.Plugin
import org.gradle.api.Project

class TestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("test plugin")
        target.task("getBuildDir") {
            this.doLast {
                println("build dir:${target.layout.buildDirectory}")
            }
            println("get build dir at configure phase")
        }
    }
}