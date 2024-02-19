package com.example.viacepapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddressApiServiceImpl {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: AddressApiService = retrofit.create(AddressApiService::class.java)


}