package com.vkhooda24.coroutinesguide.coroutines

import kotlinx.coroutines.*

suspend fun main() {
    throwExceptionExample()
    tryCatchExample()
    silenceExceptionExample()
    coroutineExceptionHandlerExample()
}

suspend fun throwExceptionExample() {

    coroutineScope {
        launch {
            delay(1000)
            throw Exception("Exception message")
        }

        launch {
            delay(1500)
            println("Launch coroutine builder")
        }
    }
}


suspend fun tryCatchExample() {

    coroutineScope {
        try {
            launch {
                delay(1000)
                throw Exception("Exception message")
            }
        } catch (e: Exception) {
            println(e.message)
        }

        launch {
            delay(1500)
            println("Launch coroutine builder")
        }
    }
}

suspend fun silenceExceptionExample() {

    supervisorScope {
        launch {
            delay(1000)
            throw Exception("Exception message")
        }
    }

    coroutineScope {
        launch {
            delay(1500)
            println("Launch coroutine builder")
        }
    }
}

suspend fun coroutineExceptionHandlerExample() {

    coroutineScope {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            println(throwable.message)
        }
        val scope = CoroutineScope(SupervisorJob() + exceptionHandler)

        scope.launch {
            delay(1000)
            throw Exception("Exception message")
        }

        scope.launch {
            delay(1500)
            println("Launch coroutine builder")
        }
        delay(2000)
    }
}