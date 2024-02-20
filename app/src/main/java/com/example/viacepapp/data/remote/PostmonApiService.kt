package com.example.viacepapp.data.remote

import com.example.viacepapp.data.model.PostmonDTO
import com.example.viacepapp.data.model.ViaCepDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PostmonApiService {
    @GET("{cep}")
    suspend fun getAddress(@Path("cep") cep: String): PostmonDTO
}
