package com.vkhooda24.unittestlib

import androidx.annotation.VisibleForTesting

class NucleusViewModel(private val nucleusDataSource: NucleusDataSource) {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getNucleusViewModelName(): String {
        return NucleusViewModel::class.java.simpleName
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun getApiResponse(condition: Boolean): String {
        return nucleusDataSource.getResponse(condition)
    }

    fun getImageApiResponse(): String {
        nucleusDataSource.getResponse(true)
        return nucleusDataSource.getImageAPIResponse()
    }

}