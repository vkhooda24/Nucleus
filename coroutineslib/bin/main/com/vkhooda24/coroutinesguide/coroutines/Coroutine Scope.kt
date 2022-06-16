package com.vkhooda24.coroutinesguide.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

suspend fun main() {
    val context = EmptyCoroutineContext + Dispatchers.Default
    //Uses Job()
    val scope = CoroutineScope(context)
    scope.launch {
        println("Coroutine launch builder is Coroutine scope's extension function")
    }.join()

    // Uses no job
    GlobalScope.launch { }

    // Use SupervisorJob()
    // MainScope().launch {} <- Use main thread, throws exception in main() function

    showSomeData()
}

suspend fun showSomeData() = coroutineScope {
    val data = async(Dispatchers.IO) { // <- extension on current scope
        // ... load some UI data for the Main thread ...
    }

    withContext(Dispatchers.Default) {
        reviewPR()
        val result = data.await()
        println("Result: $result")
    }
}