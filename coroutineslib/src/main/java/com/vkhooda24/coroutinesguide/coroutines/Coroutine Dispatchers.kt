package com.vkhooda24.coroutinesguide.coroutines

import kotlinx.coroutines.*

@OptIn(ExperimentalStdlibApi::class)
suspend fun main() {

    val scope = CoroutineScope(Dispatchers.Default)

    scope.launch(CoroutineName("First launch builder")) {
        println("Dispatcher details: " + kotlin.coroutines.coroutineContext[CoroutineDispatcher])
        println("Coroutine Name: " + kotlin.coroutines.coroutineContext[CoroutineName]?.name)
    }.join()

    println("No. of processors: ${Runtime.getRuntime().availableProcessors()}")
}