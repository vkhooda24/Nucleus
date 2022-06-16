@file:OptIn(ExperimentalTime::class)

package com.vkhooda24.coroutinesguide.coroutines

import kotlinx.coroutines.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

suspend fun main() {
    println("####### sequentialCalls() #######")
    sequentialCalls()
    println("####### asynchronousCalls() #######")
    asynchronousCalls()
    println("####### asynchronousCallsWithSubsequentDependencies() #######")
    asynchronousCallsWithSubsequentDependencies()
    println("####### timeMeasurementAsynchronousCallsWithSubsequentDependencies() #######")
    timeMeasurementAsynchronousCallsWithSubsequentDependencies()
    println("####### timeMeasurementForSequentialCalls() #######")
    timeMeasurementForSequentialCalls()
}

// Sequential calls
suspend fun sequentialCalls() {

    //use withContext or coroutineScope suspend functions
    //cleaner to use instead of using async builder function
    withContext(Dispatchers.Default) {
        println("Sequential scope call  1")
        delay(500)
    }
    coroutineScope {
        delay(300)
        println("Sequential scope call 2")
    }
}

//asynchronus call
suspend fun asynchronousCalls() {

    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        launch {
            val result = withContext(Dispatchers.Default) {
                delay(2000)
                println("Async scope call 1")
                40
            }
            println(result)
        }

        launch {
            println(withContext(Dispatchers.Default) {
                println("Async scope call  2")
                60
            })
        }
    }.join()
}

//asynchronus call but B depends on A call
suspend fun asynchronousCallsWithSubsequentDependencies() {

    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        val a = async {
            val result = withContext(Dispatchers.Default) {
                delay(1000)
                println("Async scope call 1")
                40
            }
            println(result)
            result
        }

        val b = async {
            val result = coroutineScope {
                println("Async scope call  2")
                60
            }
            println(result)
            result
        }

        addResults(a, b)

    }.join()
}

suspend fun addResults(deferredA: Deferred<Int>, deferredB: Deferred<Int>) {

    val a = deferredA.await()
    val b = deferredB.await()
    println("Sum of two numbers: ${a + b}")
}


suspend fun timeMeasurementAsynchronousCallsWithSubsequentDependencies() {

    val scope = CoroutineScope(Dispatchers.Default)
    withContext(Dispatchers.Default) {
        val totalTime = measureTime {
            val a = async {
                val time1 = measureTime {
                    val result = withContext(Dispatchers.Default) {
                        delay(1000)
                        println("Async scope call 1")
                        40
                    }
                    println(result)
                }
                println("measure time: $time1")
                time1
            }

            val b = async {
                val time2 = measureTime {
                    val result = withContext(Dispatchers.Default) {
                        delay(500)
                        println("Async scope call  2")
                        60
                    }
                    println(result)
                }
                println("measure time: $time2")
                time2
            }

            withContext(Dispatchers.Default) {
                println("Both async operation time: ${a.await() + b.await()}")
            }
        }
        println("Total time: $totalTime")
    }
}

suspend fun timeMeasurementForSequentialCalls() {
    println("Total time for sequential calls: " + measureTime { sequentialCalls() })
}