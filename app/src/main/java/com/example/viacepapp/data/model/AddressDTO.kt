package com.example.viacepapp.data.model

import com.google.gson.annotations.SerializedName

data class AddressDTO(
    @SerializedName("cep") val cep: String,
    @SerializedName("logradouro") val street: String,
    @SerializedName("complemento") val complement: String,
    @SerializedName("bairro") val neighborhood: String,
    @SerializedName("localidade") val city: String,
    @SerializedName("uf") val state: String,
    @SerializedName("ibge") val ibge: String,
    @SerializedName("gia") val gia: String,
    @SerializedName("ddd") val ddd: String,
    @SerializedName("siafi") val siafi: String
)