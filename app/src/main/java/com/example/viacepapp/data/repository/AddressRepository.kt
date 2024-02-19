package com.example.viacepapp.data.repository

import com.example.viacepapp.data.model.AddressDTO
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    fun getAddress(cep: String): Flow<AddressDTO>
}