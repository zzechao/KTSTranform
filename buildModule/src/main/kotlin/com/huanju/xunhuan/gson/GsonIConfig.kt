package com.huanju.xunhuan.gson

import com.android.build.api.instrumentation.InstrumentationParameters
import org.gradle.api.tasks.Input


/**
 * @author:zhouz
 * @date: 2024/4/22 14:38
 * description：TODO
 */
open class GsonIConfig : InstrumentationParameters {

    @get:Input
    var hookClass = "com.google.gson.stream.JsonReader"

    @get:Input
    var hookMethod = "nextString"

    @get:Input
    var fixClass = "com.duowan.makefriends.framework.util.gson.GSonHelper"
}