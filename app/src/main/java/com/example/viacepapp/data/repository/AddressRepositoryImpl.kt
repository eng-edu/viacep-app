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

    override fun getAddressViaCep(cep: String) = flow {
        emit(viaCepApiService.getAddress(cep))
    }

    override fun getAddressPostmon(cep: String) = flow {
        emit(postmonApiService.getAddress(cep))
    }

    override fun getAddressOpenCep(cep: String) = flow {
        emit(openCepApiService.getAddress(cep))
    }

    override fun getAddressWidnet(cep: String) = flow {
        emit(widnetApiService.getAddress(cep))
    }
}