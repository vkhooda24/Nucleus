package com.vkhooda24.coroutinesguide.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

@OptIn(ExperimentalStdlibApi::class)
suspend fun main() {

    val coroutineName = CoroutineName("Name: EmptyCoroutineContext") + EmptyCoroutineContext
    val job = GlobalScope.launch(coroutineName) {
        val dispatcher = coroutineContext[CoroutineDispatcher]
        val name = coroutineContext[CoroutineName]?.name
        println("Coroutine Context details: $name , Dispatcher: $dispatcher")
    }
    job.join()
}