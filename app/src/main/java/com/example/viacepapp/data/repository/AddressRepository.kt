package com.example.viacepapp.data.repository

import com.example.viacepapp.data.model.OpenCepDTO
import com.example.viacepapp.data.model.PostmonDTO
import com.example.viacepapp.data.model.ViaCepDTO
import com.example.viacepapp.data.model.WidnetDTO
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    fun getAddressViaCep(cep: String): Flow<ViaCepDTO>
    fun getAddressPostmon(cep: String): Flow<PostmonDTO>
    fun getAddressOpenCep(cep: String): Flow<OpenCepDTO>
    fun getAddressWidnet(cep: String): Flow<WidnetDTO>
}