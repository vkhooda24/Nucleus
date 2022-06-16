package com.vkhooda24.playgroundlib

fun main() {
    println("Playground JVM library")

    val name = "Kotlin"

    val output = name.let {
        it.length
    }

    val output2 = name.also {

    }

    val output1 = name.run {
        length
    }

    val output3 = name.apply {

    }

}