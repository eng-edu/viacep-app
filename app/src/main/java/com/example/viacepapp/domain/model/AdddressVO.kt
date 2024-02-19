package com.example.viacepapp.domain.model

data class AddressVO(
    val cep: String,
    val street: String,
    val complement: String,
    val neighborhood: String,
    val city: String,
    val state: String,
    val ibge: String,
    val gia: String,
    val ddd: String,
    val siafi: String
)
