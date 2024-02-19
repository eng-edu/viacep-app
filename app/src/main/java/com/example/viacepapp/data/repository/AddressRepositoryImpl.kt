package com.example.viacepapp.data.repository

import com.example.viacepapp.data.remote.AddressApiService
import kotlinx.coroutines.flow.flow

class AddressRepositoryImpl(private val apiService: AddressApiService) : AddressRepository {
    override fun getAddress(cep: String) = flow { emit(apiService.getAddress(cep)) }

}