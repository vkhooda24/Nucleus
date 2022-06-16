package com.vkhooda24.coroutinesguide.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

@OptIn(ExperimentalStdlibApi::class)
suspend fun main() {
//    Thread.sleep(10000)
    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
//        delay(10000)
    }
    println("Dispatcher: ${coroutineContext[CoroutineDispatcher]}")
}