package com.example.viacepapp.data.remote

import com.example.viacepapp.data.model.AddressDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface AddressApiService {
    @GET("/ws/{cep}/json/")
    suspend fun getAddress(@Path("cep") cep: String): AddressDTO
}