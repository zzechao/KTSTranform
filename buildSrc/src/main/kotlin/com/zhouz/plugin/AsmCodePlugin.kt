package com.zhouz.plugin

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.AppPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project


/**
 * @author:zhouz
 * @date: 2024/7/24 17:33
 * description：ASM code
 */

class AsmCodePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        Logger.make(project)
        project.plugins.withType(AppPlugin::class.java) {
            val androidComponents = project.extensions.getByType(AndroidComponentsExtension::class.java)
            androidComponents.onVariants { variant ->
                variant.transformClassesWith(HookVisitorFactory::class.java, InstrumentationScope.ALL) {

                }
                variant.setAsmFramesComputationMode(FramesComputationMode.COPY_FRAMES)
            }
        }
    }
}