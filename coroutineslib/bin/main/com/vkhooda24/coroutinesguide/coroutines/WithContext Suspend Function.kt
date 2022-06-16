package com.vkhooda24.coroutinesguide.coroutines

import kotlinx.coroutines.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {

    val measureTime = measureTime {
        runBlocking(Dispatchers.Default) {
            launch {
//            withContext(Dispatchers.Default) {
                delay(300)
                println("Launch 1")
            }
            launch {
//            withContext(Dispatchers.Default) {
                delay(600)
                println("Launch 2")
            }
            withContext(Dispatchers.Default){
                delay(400)
                println("With context output")
            }
            launch {
//            withContext(Dispatchers.Default) {
                println("Launch 3")
            }
            launch {
//                    withContext(Dispatchers.Default) {
//            coroutineScope {
//                    println("before error")
//                    throw Throwable("Exception")
                checkEmails(2, "")
                reviewPR()
            }
            println("Plain output ")
//                }
//            launch {
            checkMessage()
//            }
        }
    }
    println(measureTime)

}