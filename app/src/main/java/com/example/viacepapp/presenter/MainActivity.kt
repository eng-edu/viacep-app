package com.example.viacepapp.presenter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.viacepapp.data.remote.AddressApiServiceImpl
import com.example.viacepapp.data.repository.AddressRepositoryImpl
import com.example.viacepapp.domain.useCase.GetAddressUseCaseImpl
import com.example.viacepapp.domain.util.ViewState


class MainActivity : ComponentActivity() {


    // private val viewModelFactory = AddressViewModel.newFactory()
    // private val viewModel: AddressViewModel by viewModels() { viewModelFactory }

    private lateinit var viewModel: AddressViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val serv = AddressApiServiceImpl()
        val repo = AddressRepositoryImpl(serv.apiService)
        val useCase = GetAddressUseCaseImpl(repo)
        viewModel = AddressViewModel(useCase)

        viewModel.getAddress("68459370")

        viewModel.address.observeForever {
            when (it) {
                is ViewState.Loading -> {
                    Log.i("TESTEDU", "LOADING")
                }

                is ViewState.Success -> {
                    Log.i("TESTEDU", it.data.toString())
                }

                is ViewState.Error -> {
                    Log.i("TESTEDU", it.error)
                }

                else -> {}
            }
        }

    }
}
