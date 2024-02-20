package com.example.viacepapp.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.viacepapp.data.repository.AddressRepositoryImpl
import com.example.viacepapp.domain.model.AddressVO
import com.example.viacepapp.domain.useCase.GetAddressUseCase
import com.example.viacepapp.domain.useCase.GetAddressUseCaseImpl
import com.example.viacepapp.domain.util.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddressViewModel(private val getAddressUseCase: GetAddressUseCase) : ViewModel() {

    private lateinit var _cep: String
    private val _addressState = MutableStateFlow<ViewState<AddressVO>>(ViewState.Loading)
    val addressState: StateFlow<ViewState<AddressVO>> = _addressState


    fun getAddress(cep: String) {
        _cep = cep
        viewModelScope.launch {
            try {
                val address = getAddressUseCase.execute(cep)
                _addressState.value = ViewState.Success(address)
            } catch (e: Exception) {
                _addressState.value = ViewState.Error(e.message.toString(), ::tryAgain)
            }
        }
    }

    private fun tryAgain() {
        getAddress(_cep)
    }
}