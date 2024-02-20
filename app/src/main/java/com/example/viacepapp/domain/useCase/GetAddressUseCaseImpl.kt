package com.example.viacepapp.domain.useCase

import com.example.viacepapp.data.repository.AddressRepository
import com.example.viacepapp.data.repository.AddressRepositoryImpl
import com.example.viacepapp.domain.model.AddressVO
import com.example.viacepapp.domain.model.toVo
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
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
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

class GetAddressUseCaseImpl(private val repository: AddressRepository) : GetAddressUseCase {
    override suspend fun execute(cep: String): AddressVO {
        return coroutineScope {

            val viaCepCall = async { repository.getAddressViaCep(cep).toVo() }
            val openCepCall = async { repository.getAddressOpenCep(cep).toVo() }
            val postmomCall = async { repository.getAddressPostmon(cep).toVo() }
            val widnetCall = async { repository.getAddressWidnet(cep).toVo() }


            listOf(
                viaCepCall,
                openCepCall,
                postmomCall,
                widnetCall
            ).awaitAny()

        }
    }

    suspend fun <T> List<Deferred<T>>.awaitAny(): T {
        val deferred = CompletableDeferred<T>()
        forEach { deferredJob ->
            deferredJob.invokeOnCompletion { cause ->
                cause?.let { deferred.completeExceptionally(it) }
                    ?: deferred.complete(deferredJob.getCompleted())
            }
        }
        return deferred.await()
    }
}