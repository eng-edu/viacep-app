package com.example.viacepapp.di

import com.example.viacepapp.data.remote.OpenCepApiService
import com.example.viacepapp.data.remote.PostmonApiService
import com.example.viacepapp.data.remote.ViaCepApiService
import com.example.viacepapp.data.remote.WidnetApiService
import com.example.viacepapp.data.repository.AddressRepository
import com.example.viacepapp.data.repository.AddressRepositoryImpl
import com.example.viacepapp.domain.useCase.GetAddressUseCase
import com.example.viacepapp.domain.useCase.GetAddressUseCaseImpl
import com.example.viacepapp.presenter.AddressViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viaCepModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ViaCepApiService::class.java)
    }
}

val openCepModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://opencep.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(OpenCepApiService::class.java)
    }
}

val postmonModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.postmon.com.br/v1/cep/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(PostmonApiService::class.java)
    }
}

val widenetModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://apps.widenet.com.br/busca-cep/api/cep/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(WidnetApiService::class.java)
    }
}

val appModule = module {
    single<AddressRepository> { AddressRepositoryImpl(get(), get(), get(), get()) }
    single<GetAddressUseCase> { GetAddressUseCaseImpl(get()) }
    viewModel { AddressViewModel(get()) }
}

val myModules = listOf(viaCepModule, openCepModule, postmonModule, widenetModule, appModule)




