package com.example.viacepapp.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.viacepapp.data.remote.AddressApiServiceImpl
import com.example.viacepapp.data.repository.AddressRepositoryImpl
import com.example.viacepapp.domain.model.AddressVO
import com.example.viacepapp.domain.useCase.GetAddressUseCaseImpl
import com.example.viacepapp.domain.util.ViewState
import kotlinx.coroutines.launch

class AddressViewModel(private val getAddressUseCaseImpl: GetAddressUseCaseImpl) : ViewModel() {

    private lateinit var _cep: String
    private val _address = MutableLiveData<ViewState<AddressVO>>(ViewState.Loading)
    val address: LiveData<ViewState<AddressVO>> = _address


    fun getAddress(cep: String) {
        _cep = cep
        viewModelScope.launch {
            try {
                val address = getAddressUseCaseImpl.execute(cep)
                _address.value = ViewState.Success(address)
            } catch (e: Exception) {
                _address.value = ViewState.Error(e.message.toString(), ::tryAgain)
            }
        }
    }

    private fun tryAgain() {
        getAddress(_cep)
    }

    companion object {
        fun newFactory() = viewModelFactory {
            initializer {
                val serv = AddressApiServiceImpl()
                val repo = AddressRepositoryImpl(serv.apiService)
                val useCase = GetAddressUseCaseImpl(repo)
                AddressViewModel(useCase)
            }
        }
    }

}