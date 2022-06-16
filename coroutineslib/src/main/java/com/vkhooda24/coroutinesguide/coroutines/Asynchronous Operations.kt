package com.vkhooda24.coroutinesguide.coroutines

import kotlinx.coroutines.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {

    //analogy
    //more than 1 operations to perform
    //operations can be performed sequentially or asynchronously
    //e.g., Dial in a call to speak with x company and you get automated message to stay on hold, someone will be with you in 2 min.
    // now up to you, wait there for 2 min or get done few things in meantime.
    // wait and then do other things in order is sequential operation
    // doing in meantime like reading message, checking email, approving PRs is asynchronous operations

    //asynchronous operations execution
    //making a call - Thread 1
    //doing other things in meantime - Thread 2, Thread 3 and so on

    //synchronous operation execution
    //making a call - Thread 1
    //doing other things after call finish - Thread 1

    //operations
//    val operations = arrayOf(makeAPhoneCall(), checkEmails(), checkMessage())

    val measureTime = measureTime {
        runBlocking(Dispatchers.Default) {
            launch {
//            withContext(Dispatchers.Default) {
                makeAPhoneCall()
            }
            launch {
//            coroutineScope {
//                    println("before error")
//                    throw Throwable("Exception")
                checkEmails(2, "")
                reviewPR()
            }
//                }
//            launch {
            checkMessage()
//            }
        }
    }
    println(measureTime)
}

//should a function be suspended or not?