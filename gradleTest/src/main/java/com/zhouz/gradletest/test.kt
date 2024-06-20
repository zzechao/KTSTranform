package com.zhouz.gradletest


/**
 * @author:zhouz
 * @date: 2024/5/14 17:05
 * description：TODO
 */

val n = 10

fun main(args: Array<String>) {
    var max = 0
    for (i in 1..<n) {
        println("start i:$i")
        val cur = max(i, n - i, i)
        println("end cur:$cur")
        max = maxOf(max, cur)
    }
    println(" result:$max")
}

fun max(i: Int, yu: Int, chen: Int): Int {
    println("max start i:$i yu:$yu chen:$chen")
    return if (yu == 0) {
        println("action 1")
        chen
    } else if (i < yu) {
        var max = chen
        for (j in 1..yu - i) {
            val curChen = j * chen
            println("action 2 j:$j cur:${j * chen}")
            max = maxOf(max, curChen)
            max(j, yu - j, curChen)
        }
        max
    } else {
        println("action 3")
        chen * yu
    }
}