package com.vkhooda24.coroutinesguide

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
    val measureTime = measureTime {
        makeAPhoneCall()
        checkEmails()
        checkMessage()
    }
    println(measureTime)
}

fun makeAPhoneCall() {
    //wait time 1 minute
    Thread.sleep(5000)
    println("Making a phone call...")
}

fun checkEmails() {
    Thread.sleep(500)
    println("Checking emails...")
}

fun checkMessage() {
    Thread.sleep(200)
    println("Checking messages...")
}

fun reviewPR() {
    println("Reviewing PRs...")
}