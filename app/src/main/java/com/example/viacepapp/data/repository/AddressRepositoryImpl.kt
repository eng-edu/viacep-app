package com.example.viacepapp.data.repository

import com.example.viacepapp.data.model.OpenCepDTO
import com.example.viacepapp.data.model.PostmonDTO
import com.example.viacepapp.data.model.ViaCepDTO
import com.example.viacepapp.data.model.WidnetDTO
import com.example.viacepapp.data.remote.OpenCepApiService
import com.example.viacepapp.data.remote.PostmonApiService
import com.example.viacepapp.data.remote.ViaCepApiService
import com.example.viacepapp.data.remote.WidnetApiService
import com.example.viacepapp.domain.model.AddressVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddressRepositoryImpl(
    private val viaCepApiService: ViaCepApiService,
    private val openCepApiService: OpenCepApiService,
    private val postmonApiService: PostmonApiService,
    private val widnetApiService: WidnetApiService

) : AddressRepository {
    override suspend fun getAddressViaCep(cep: String) = viaCepApiService.getAddress(cep)
    override suspend fun getAddressPostmon(cep: String) = postmonApiService.getAddress(cep)
    override suspend fun getAddressOpenCep(cep: String) = openCepApiService.getAddress(cep)
    override suspend fun getAddressWidnet(cep: String) = widnetApiService.getAddress(cep)

}