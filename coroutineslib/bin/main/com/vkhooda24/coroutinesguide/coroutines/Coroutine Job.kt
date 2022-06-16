package com.vkhooda24.coroutinesguide.coroutines

import kotlinx.coroutines.*

suspend fun main() {

    val scope = CoroutineScope(Dispatchers.Default)

    val job: Job = scope.launch {
        println("Launch coroutine")

        launch {
            delay(500)
            println("Child coroutine")
        }

        launch {
            delay(500)
            println("Child coroutine 1")
        }.cancel("Cancelled")

        launch {
            delay(200)
            cancel(CancellationException("Error", Throwable("IO error")))
            println("Child coroutine 2")//this can be executed in meantime while job is getting cancelled
        }

        launch {
            cancel(CancellationException("Error", Throwable("IO error")))
            delay(1200)
            println("Child coroutine 3")
            launch {
                println("Inner Child coroutine 3")
            }
        }
//        cancel(CancellationException("Error", Throwable("IO error")))
    }
//    job.invokeOnCompletion {
//        println(it?.message)
//    }
    job.join()
}