package com.zhouz.arouterplugin

import org.gradle.api.DefaultTask
import org.gradle.api.file.Directory
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.util.jar.JarOutputStream


/**
 * @author:zhouz
 * @date: 2024/6/20 18:17
 * description：ARouter的收集task
 */
abstract class ARouterCollectTask : DefaultTask() {
    @get:InputFiles
    abstract val allJars: ListProperty<RegularFile>

    @get:InputFiles
    abstract val allDirectories: ListProperty<Directory>

    @get:OutputFile
    abstract val output: RegularFileProperty

    @TaskAction
    fun taskAction() {
        //val pool = ClassPool(ClassPool.getDefault())
        val targetList: List<ScanSetting> = listOf(
            ScanSetting("IRouteRoot"),
            ScanSetting("IInterceptorGroup"),
            ScanSetting("IProviderGroup"),
        )
        JarOutputStream(output.asFile.get().outputStream()).use {

        }
    }
}