@file:OptIn(ExperimentalTime::class, ExperimentalCoroutinesApi::class)

package com.vkhooda24.coroutinesguide.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

private const val TIMES = 1_000

suspend fun main() {
    problemStatement()
    blockingSolution()
    atomicIntegerSolution()
    atomicIntegerWithMultipleOperationProblemStatement()
    coarseGrainedLimitedParallelismSolution()
    fineGrainedLimitedParallelismSolution()
    mutexSolution()
}

suspend fun mutexSolution() {

    val mutex = Mutex()
    var counter = 0
    val measurement = measureTime {
        withContext(Dispatchers.Default) {
            repeat(TIMES) {
                launch {
                    mutex.lock()
                    counter++
                    mutex.unlock()
                }
            }
        }
    }
    println("Mutex solution output: $counter")
    println("Mutex operation measurement: $measurement")

    //kotlinx.coroutines.sync
    //Mutex.withLock {  } // implicitly calls unlock if any exception is thrown

}

private suspend fun problemStatement() {
    var counter = 0
    val measurement = measureTime {
        withContext(Dispatchers.Default) {
            repeat(TIMES) {
                launch {
                    counter++
                }
            }
        }
    }
    println("Problem statement: Thread unsafe operation output: $counter")
    println("Problem statement: Thread unsafe operation measurement: $measurement")
}

private suspend fun blockingSolution() {
    var counter = 0
    val lock = Any()
    val measurement = measureTime {
        withContext(Dispatchers.Default) {
            repeat(TIMES) {
                launch {
                    synchronized(lock) {
                        counter++
                    }
                }
            }
        }
    }
    println("Synchronized operation output: $counter")
    println("Synchronized operation measurement: $measurement")
}

private suspend fun atomicIntegerSolution() {
    val counter = AtomicInteger(0)
    val measurement = measureTime {
        withContext(Dispatchers.Default) {
            repeat(TIMES) {
                launch {
                    counter.incrementAndGet()
                }
            }
        }
    }
    println("Atomic operation output: ${counter.get()}")
    println("Atomic operation measurement: $measurement")
}

private suspend fun atomicIntegerWithMultipleOperationProblemStatement() {
    val counter = AtomicInteger(0)
    val measurement = measureTime {
        withContext(Dispatchers.Default) {
            repeat(TIMES) {
                launch {
                    counter.set(counter.get() + 1)
                }
            }
        }
    }
    println("Problem statement: Atomic with multiple operation output: ${counter.get()}")
    println("Problem statement: Atomic with multiple operation measurement: $measurement")
}

private suspend fun coarseGrainedLimitedParallelismSolution() {
    val dispatcher = Dispatchers.Default.limitedParallelism(1)
    var counter = 0
    val measurement = measureTime {
        withContext(dispatcher) {
            repeat(TIMES) {
                launch {
                    counter++
                }
            }
        }

        withContext(dispatcher) {
            repeat(TIMES) {
                launch {
                    counter--
                }
            }
        }
    }
    println("Coarse grained limited parallelism output: $counter")
    println("Coarse grained limited parallelism measurement: $measurement")
}

private suspend fun fineGrainedLimitedParallelismSolution() {
    val dispatcher = Dispatchers.Default.limitedParallelism(1)
    var counter = 0
    val measurement = measureTime {
        repeat(TIMES) {
            withContext(dispatcher) {
                counter++
            }
        }

        repeat(TIMES) {
            withContext(dispatcher) {
                counter--
            }
        }
    }
    println("Fine grained limited parallelism output: $counter")
    println("Fine grained limited parallelism measurement: $measurement")
}