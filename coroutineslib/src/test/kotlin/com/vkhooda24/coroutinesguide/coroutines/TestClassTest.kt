package com.vkhooda24.coroutinesguide.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestClassTest {

    @get:Rule
    var mainCoroutineRule = CoroutineRule()

    private lateinit var device: Device
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        device = Device()
        //use given test dispatcher
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        //reset to the original main dispatcher
        Dispatchers.resetMain()
        //make sure no other work is running
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun test() {
        runBlocking {
            val sum = 4
            assertEquals(sum, 2 + 2)
        }
    }

    @Test
    fun `check the device type`() {
//        val scope = TestCoroutineScope()
        runBlocking{
            val actualDeviceType = device.getDeviceType()
            assertEquals("ipad", actualDeviceType)
        }
    }
}