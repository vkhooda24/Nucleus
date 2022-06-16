package com.vkhooda24.coroutinesguide.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    val result = suspendCoroutine<String> { continuation ->
        println("Enter suspended coroutine")
        //continuation.resumeWith(Result.success("Suspended coroutine output 1"))
        continuationBlock(continuation)
        println("Exit suspended coroutine")
    }
    println(result)
}

fun continuationBlock(continuation: Continuation<String>) {
    continuation.resumeWith(Result.success("Suspended coroutine output 2"))
}
// Twice call resumeWith()
// Exception in thread "main" java.lang.IllegalStateException: Already resumed