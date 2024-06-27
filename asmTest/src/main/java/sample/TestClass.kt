package sample

import java.io.FileNotFoundException
import java.io.IOException


/**
 * @author:zhouz
 * @date: 2024/6/27 19:44
 * description：kotlin asm 尝试
 */
class TestClass {

    val constant_field = 10

    val non_constant = null

    @Throws(IOException::class, FileNotFoundException::class)
    fun test() {

    }
}