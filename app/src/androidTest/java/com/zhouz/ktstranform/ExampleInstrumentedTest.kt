package com.zhouz.ktstranform

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.zhouz.ktstranform", appContext.packageName)
    }

    @Test
    fun testHttp() {
        val url = "http://restapi.amap.com/v3/weather/weatherInfo?city=长沙&key=13cb58"
        url.toHttpUrlOrNull()?.let {
            println("protocol:${it.port}")
            println("ip:${it.host}")
            println("port:${it.port}")
            println("path:${it.encodedPath}")
            println("host:${it.host}")
        }
    }
}