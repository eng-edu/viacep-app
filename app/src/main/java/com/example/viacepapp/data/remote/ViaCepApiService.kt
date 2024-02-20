package com.example.viacepapp.data.remote

import com.example.viacepapp.data.model.ViaCepDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepApiService {
    @GET("/sws/{cep}/json/")
    suspend fun getAddress(@Path("cep") cep: String): ViaCepDTO
}
