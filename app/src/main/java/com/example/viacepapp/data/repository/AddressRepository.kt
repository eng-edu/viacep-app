package com.example.viacepapp.data.repository

import com.example.viacepapp.data.model.OpenCepDTO
import com.example.viacepapp.data.model.PostmonDTO
import com.example.viacepapp.data.model.ViaCepDTO
import com.example.viacepapp.data.model.WidnetDTO
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    suspend fun getAddressViaCep(cep: String): ViaCepDTO
    suspend fun getAddressPostmon(cep: String): PostmonDTO
    suspend fun getAddressOpenCep(cep: String): OpenCepDTO
    suspend fun getAddressWidnet(cep: String): WidnetDTO
}