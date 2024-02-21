package com.example.viacepapp.domain.model

data class Response(
    val success: Boolean = false,
    val addressVO: AddressVO? = null,
    val errorMsg: String? = null
)