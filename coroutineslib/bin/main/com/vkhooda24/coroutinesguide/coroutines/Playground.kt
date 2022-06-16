package com.vkhooda24.coroutinesguide.coroutines

import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    val result = suspendCoroutine<String> { continuation ->
        println("Enter suspended coroutine")
        continuation.resumeWith(Result.success("Suspended coroutine output 1"))
        println("Exit suspended coroutine")
    }
    println(result)
}