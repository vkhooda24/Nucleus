package com.vkhooda24.coroutinesguide.coroutines

import kotlinx.coroutines.delay

// not suspended
// no continuation param for this - check bytecode
suspend fun makeAPhoneCall() {
    //wait time 1 minute
    delay(700)
    println("Making a phone call...")
//    println("Thread name:${Thread.currentThread()}")
    //yield()
}

suspend fun checkEmails(one: Int, two: String) {
//    yield()
    delay(4000)
    println("Checking emails...")
    println("Thread name:${Thread.currentThread()}")
}

suspend fun checkMessage() {
    delay(800)
    println("Checking messages...")
    println("Thread name:${Thread.currentThread()}")
}

fun reviewPR() {
    println("Reviewing PRs...")
}