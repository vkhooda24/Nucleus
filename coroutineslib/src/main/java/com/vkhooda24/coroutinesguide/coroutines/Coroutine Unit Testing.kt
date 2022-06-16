package com.vkhooda24.coroutinesguide.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Device {

    //phone, ipad, laptop
    suspend fun getDeviceType(): String {
//        val scope = CoroutineScope(Dispatchers.Default)
        return withContext(Dispatchers.IO) {
            "ipad"
        }
    }

    fun getDeviceDetails() {

    }
}