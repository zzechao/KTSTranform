package com.zhouz.proguard

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.util.Random
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ThreadLocalRandom
import javax.inject.Inject

/**
 * Created by Sergey Chuprin on 16/01/2019.
 */
open class ProguardR8DictionaryGeneratorTask @Inject constructor(
    private val dictionaryNames: List<String>,
    private val linesCountInDictionary: Int,
    private val minLineLength: Int,
    private val maxLineLength: Int,
    private val alphabetExtension: MutableList<String>
) : DefaultTask() {

    companion object {
        const val TAG = "ProguardR8DictionaryGeneratorTask"
        const val NAME = "generateProguardDictionaries"
    }

    private var loggingExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        val buffer = StringBuffer()
        throwable.stackTrace.map {
            buffer.append("${it.methodName}--${it.className}")
        }
        Logger.e("$TAG action error:$buffer")
    }

    private val fileScope = CoroutineScope(
        SupervisorJob() + loggingExceptionHandler + CoroutineName("dictionaryFile")
    )


    private val alphabet by lazy {
        (('a'..'z') + ('A'..'Z') + ('0'..'9') + alphabetExtension).joinToString("")
    }

    @TaskAction
    fun run() {
        Logger.i("$TAG action start")
        val countDownLatch = CountDownLatch(dictionaryNames.size)
        dictionaryNames.forEach {
            generate(it, countDownLatch)
        }
        countDownLatch.await()
        Logger.i("$TAG action finish")
    }

    private fun generate(dictionaryName: String, countDownLatch: CountDownLatch) {
        fileScope.launch {
            val current = System.currentTimeMillis()
            Logger.i("$TAG generate start $dictionaryName")

            val random = ThreadLocalRandom.current()

            Logger.i("$TAG generate dictionarySet $dictionaryName start durTime:${System.currentTimeMillis() - current}")

            val dictionarySet = (0 until linesCountInDictionary)
                .fold(mutableSetOf<String>()) { set, _ ->
                    set.apply {
                        val lineLength = random.nextInt(minLineLength, maxLineLength)
                        add(randomizeString(lineLength, alphabet, random))
                    }
                }

            Logger.i("$TAG generate dictionarySet $dictionaryName finish durTime:${System.currentTimeMillis() - current}")

            File(project.projectDir, "$dictionaryName.txt").run {
                parentFile.mkdirs()
                writeText(dictionarySet.joinToString("\n"))
            }
            countDownLatch.countDown()

            Logger.i("$TAG generate finish $dictionaryName  durTime:${System.currentTimeMillis() - current}")
        }
    }

    private fun randomizeString(lineLength: Int, alphabet: String, random: Random): String {
        return (1..lineLength)
            .map { alphabet[random.nextInt(alphabet.length)] }
            .joinToString("")
    }

}