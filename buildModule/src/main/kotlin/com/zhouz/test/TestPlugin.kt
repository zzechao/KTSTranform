package com.zhouz.test

import org.gradle.api.Plugin
import org.gradle.api.Project


/**
 * @author:zhouz
 * @date: 2024/4/28 13:05
 * description
 */
class TestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("test plugin")
        target.task("getBuildDir") {
            println("get build dir at configure phase")
            it.doLast {
                println("build dir:${target.layout.buildDirectory}")
            }
        }
    }
}