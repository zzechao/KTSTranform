package com.zhouz.plugin

import com.android.build.api.instrumentation.InstrumentationParameters
import org.gradle.api.tasks.Input


/**
 * @author:zhouz
 * @date: 2024/7/24 17:36
 * description：hook 参数配置
 */
open class HookParameters : InstrumentationParameters {

    @get:Input
    var hookClass = ""

    @get:Input
    var hookMethod = ""
}