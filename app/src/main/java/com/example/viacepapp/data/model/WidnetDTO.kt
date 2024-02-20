package com.example.viacepapp.data.model

import com.google.gson.annotations.SerializedName

data class WidnetDTO(
    @SerializedName("address") val logradouro: String,
    @SerializedName("district") val bairro: String,
    @SerializedName("city") val cidade: String,
    @SerializedName("state") val estado: String
)