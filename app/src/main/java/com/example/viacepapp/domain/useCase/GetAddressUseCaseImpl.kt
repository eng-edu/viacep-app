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

        /**
         * flatMapMerge é um operador do Kotlin Flow que permite combinar vários fluxos em um único fluxo,
         * onde os elementos dos fluxos originais são emitidos em ordem, mas as emissões ocorrem concorrentemente.
         *
         * Quando você aplica flatMapMerge a um fluxo de entrada, ele aceita um lambda que mapeia cada elemento do
         * fluxo para um novo fluxo. Em seguida, mescla todos esses fluxos resultantes em um único fluxo, emitindo
         * os elementos à medida que estão prontos, independentemente da ordem de chegada dos elementos originais.
         */
        val combinedFlow = flowOf(viaCepFlow, openCepFlow, postmonFlow, widnetFlow)
            .flatMapMerge { it }

        combinedFlow.collect {
            emit(it)
        }
    }

}