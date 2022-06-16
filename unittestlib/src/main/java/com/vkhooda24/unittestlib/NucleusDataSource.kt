package com.vkhooda24.unittestlib


interface ResponseInterface {
    fun onSuccess(): String
    fun onFailure(): String
}

class NucleusDataSource {

    fun getResponse(isSuccess: Boolean): String {
        val obj = object : ResponseInterface {
            override fun onSuccess(): String {
                return "onSuccess"
            }

            override fun onFailure(): String {
                return "onFailure"
            }
        }
        return if (isSuccess) obj.onSuccess() else obj.onFailure()
    }

    fun getImageAPIResponse(): String {
        return "Image API response"
    }
}