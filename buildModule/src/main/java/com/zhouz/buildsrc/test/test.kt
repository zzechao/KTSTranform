package com.zhouz.buildsrc.test


/**
 * @author:zhouz
 * @date: 2024/4/22 14:19
 * description：TODO
 */
fun test7(runnable: Runnable?) {
    val thread = Thread(runnable)
    thread.name = "test7"
    thread.start()
}
