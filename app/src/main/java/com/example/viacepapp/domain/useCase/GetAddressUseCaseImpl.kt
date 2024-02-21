package com.example.viacepapp.domain.useCase

import com.example.viacepapp.data.repository.AddressRepository
import com.example.viacepapp.domain.model.AddressVO
import com.example.viacepapp.domain.model.toVo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf


class GetAddressUseCaseImpl(private val repository: AddressRepository) : GetAddressUseCase {
    override suspend fun execute(cep: String): AddressVO {
        return ranceFlow(cep).first()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun ranceFlow(cep: String) = flow {
        val viaCepFlow = flow { emit(repository.getAddressViaCep(cep).toVo()) }
        val openCepFlow = flow { emit(repository.getAddressOpenCep(cep).toVo()) }
        val postmonFlow = flow { emit(repository.getAddressPostmon(cep).toVo()) }
        val widnetFlow = flow { emit(repository.getAddressWidnet(cep).toVo()) }

        val combinedFlow = flowOf(viaCepFlow, openCepFlow, postmonFlow, widnetFlow)
            .flatMapMerge { it }

        combinedFlow.collect {
            emit(it)
        }
    }

}