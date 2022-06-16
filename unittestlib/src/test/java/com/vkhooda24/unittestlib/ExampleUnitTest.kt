package com.vkhooda24.unittestlib

import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val dataSource = mockk<NucleusDataSource>()

    private val viewModel by lazy {
        NucleusViewModel(dataSource)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `check view model name`() {
        assertEquals("NucleusViewModel", viewModel.getNucleusViewModelName())
    }
}