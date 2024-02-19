package com.example.viacepapp.domain.useCase

import com.example.viacepapp.domain.model.AddressVO

interface GetAddressUseCase {
    suspend fun execute(cep: String): AddressVO
}