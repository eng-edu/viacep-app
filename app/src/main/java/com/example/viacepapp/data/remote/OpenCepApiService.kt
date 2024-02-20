package com.example.viacepapp.data.remote

import com.example.viacepapp.data.model.OpenCepDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenCepApiService {
    @GET("{cep}")
    suspend fun getAddress(@Path("cep") cep: String): OpenCepDTO
}
