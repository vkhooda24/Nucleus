package com.vkhooda24.unittestlib

import io.mockk.*
import org.junit.Assert
import org.junit.Test

class NucleusViewModelTest {

    /**
     * mockk object creates "Strict" object by default means stubbing method call required for all of its methods.
     * Providing "relaxed" argument value as true allow to create relaxed mock object where
     * pre-defined expectation call(stubbing method call) is not required. It becomes optional.
     *
     */
    private val dataSource = mockk<NucleusDataSource>()
//    private val dataSource = mockk<NucleusDataSource>(relaxed = true)

    private val nucleusViewModel = NucleusViewModel(dataSource)
    private val spyResponseInterface = spyk<ResponseInterface>()
    private val mockResponseInterface = mockk<ResponseInterface>()

    @Test
    fun `check Nucleus View Model Name`() {
        val actualName = nucleusViewModel.getNucleusViewModelName()
        Assert.assertEquals("NucleusViewModel", actualName)
    }

    @Test
    fun `relaxed mockk object verification`() {
        val dataSource = mockk<NucleusDataSource>(relaxed = true)
        dataSource.getResponse(true)
        verify(exactly = 1) { dataSource.getResponse(true) }

        confirmVerified(dataSource)
    }

    @Test
    fun `behavior verification - set expectation on mocked method call to answer when called`() {

        //set pre-defined expectation result ie. "onSuccess"  for getResponse() mocked method call
        every { dataSource.getResponse(any()) }.returns("onSuccess")
        Assert.assertEquals("onSuccess", nucleusViewModel.getApiResponse(true))

        //set pre-defined expectation result ie. "onFailure" for getResponse() mocked method call
        every { dataSource.getResponse(any()) }.returns("onFailure")//expectation set
        Assert.assertEquals("onFailure", nucleusViewModel.getApiResponse(false))
    }

    @Test
    fun `verify - mocked method call verified`() {
        //set pre-defined expectation result for getResponse() mocked method call
        every { dataSource.getResponse(any()) }.returns("onSuccess")
        nucleusViewModel.getApiResponse(true)

        //method called once in nucleusViewModel.getApiResponse(true) method
        verify(exactly = 1) { dataSource.getResponse(true) }
    }

    @Test
    fun `spy and mock object methods verification`() {

        //Expectation set for spy object's method call
        every { spyResponseInterface.onSuccess() }.returns("")
        //Expectation set for mocked object's method call
        every { mockResponseInterface.onSuccess() }.returns("")
        every { mockResponseInterface.onFailure() }.returns("")

        spyResponseInterface.onSuccess()//Calls mocked method call for expected result
        spyResponseInterface.onFailure()//Call real method call for expected result

        //all three mocked method calls return pre-defined result/stubbed result
        mockResponseInterface.onSuccess()
        mockResponseInterface.onSuccess()
        mockResponseInterface.onFailure()

        verify {
            spyResponseInterface.onSuccess()
            spyResponseInterface.onFailure()
            mockResponseInterface.onSuccess()
            mockResponseInterface.onSuccess()
            mockResponseInterface.onFailure()
        }

        //confirms all methods have verified on not of a given class
        confirmVerified(spyResponseInterface)
        confirmVerified(mockResponseInterface)
    }

    @Test
    fun `image api test verification`() {

        every { dataSource.getResponse(isSuccess = true) }.returns("onSuccess")
        every { dataSource.getImageAPIResponse() }.returns("Image API response")

        Assert.assertEquals("Image API response", nucleusViewModel.getImageApiResponse())

        verify(exactly = 1) { dataSource.getImageAPIResponse() }
        verify(exactly = 1) { dataSource.getResponse(true) }
        confirmVerified(dataSource)
    }
}