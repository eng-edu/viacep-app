package com.example.viacepapp.domain.useCase

import com.example.viacepapp.data.repository.AddressRepository
import com.example.viacepapp.data.repository.AddressRepositoryImpl
import com.example.viacepapp.domain.model.AddressVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

class GetAddressUseCaseImpl(private val repository: AddressRepository) : GetAddressUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun execute(cep: String): AddressVO {
        return coroutineScope {

            val viaCepDeferred = async(Dispatchers.IO) {
                try {

                    //delay(5000L)

                    repository.getAddressViaCep(cep)
                        .map { AddressVO("viaCep", it.logradouro, it.bairro, it.cidade, it.estado) }

                } catch (e: Exception) {
                    null
                }
            }

            val openCepDeferred = async(Dispatchers.IO) {
                try {
                    repository.getAddressOpenCep(cep).map {
                        AddressVO(
                            "openCep", it.logradouro, it.bairro, it.cidade, it.estado
                        )
                    }
                } catch (e: Exception) {
                    null
                }
            }
            val postmomDeferred = async(Dispatchers.IO) {
                try {
                    repository.getAddressPostmon(cep).map {
                        AddressVO(
                            "postmom", it.logradouro, it.bairro, it.cidade, it.estadoInfo.nome
                        )
                    }
                } catch (e: Exception) {
                    null
                }
            }

            val widnetDeferred = async(Dispatchers.IO) {
                try {

                    repository.getAddressWidnet(cep)
                        .map { AddressVO("widnet", it.logradouro, it.bairro, it.cidade, it.estado) }
                } catch (e: Exception) {
                    null
                }

            }


            // Combina os resultados dos três serviços em um fluxo
            val combineFlow = flowOf(
                viaCepDeferred.await(),
                openCepDeferred.await(),
                postmomDeferred.await(),
                widnetDeferred.await()
            )

            //sempre chama o primeiro da lista e trava caso esse primeiro demore

            // Retorna o primeiro resultado que estiver disponível
            combineFlow.first() ?: flow { AddressVO("", "", "", "", "") }

        }.single()
    }
}