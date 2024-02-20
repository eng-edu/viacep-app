package com.example.viacepapp.di

import com.example.viacepapp.data.remote.AddressApiService
import com.example.viacepapp.data.repository.AddressRepository
import com.example.viacepapp.data.repository.AddressRepositoryImpl
import com.example.viacepapp.domain.useCase.GetAddressUseCase
import com.example.viacepapp.domain.useCase.GetAddressUseCaseImpl
import com.example.viacepapp.presenter.AddressViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val moduleDeclaration = module {
    single {
        Retrofit.Builder().baseUrl("https://viacep.com.br/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    single { get<Retrofit>().create(AddressApiService::class.java) }
    single<AddressRepository> { AddressRepositoryImpl(get()) }
    single<GetAddressUseCase> { GetAddressUseCaseImpl(get()) }
    viewModel { AddressViewModel(get()) }
}