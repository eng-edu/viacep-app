package com.example.viacepapp.domain.useCase

import com.example.viacepapp.data.repository.AddressRepositoryImpl
import com.example.viacepapp.domain.model.AddressVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.withContext

class GetAddressUseCaseImpl(private val repository: AddressRepositoryImpl) : GetAddressUseCase {
    override
    suspend fun execute(cep: String): AddressVO {
        val addressVO: AddressVO
        withContext(Dispatchers.IO) {
            val addressDTO = repository.getAddress(cep).single()

            addressDTO.let {
                addressVO = AddressVO(
                    it.cep,
                    it.street,
                    it.complement,
                    it.neighborhood,
                    it.city,
                    it.state,
                    it.ibge,
                    it.gia,
                    it.ddd,
                    it.siafi
                )
            }
        }

        return addressVO
    }
}