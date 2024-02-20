package com.example.viacepapp.data.model

import com.google.gson.annotations.SerializedName

data class ViaCepDTO(
    @SerializedName("logradouro") val logradouro: String,
    @SerializedName("bairro") val bairro: String,
    @SerializedName("localidade") val cidade: String,
    @SerializedName("uf") val estado: String
)