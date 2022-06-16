package com.vkhooda24.coroutinesguide

fun main() {

    val sequence = sequence {
        println("One")
        yield(1)

        println("Two")
        yield(2)

        println("Three")
        yield(3)

        println("Done")
    }

    for (value in sequence) {
        println("Value is: $value")
    }
}