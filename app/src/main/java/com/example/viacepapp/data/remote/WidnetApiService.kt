package com.example.viacepapp.data.remote

import com.example.viacepapp.data.model.ViaCepDTO
import com.example.viacepapp.data.model.WidnetDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface WidnetApiService {
    @GET("{cep}.json")
    suspend fun getAddress(@Path("cep") cep: String): WidnetDTO
}
