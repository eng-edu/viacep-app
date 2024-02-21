package com.example.viacepapp.data.model

import com.google.gson.annotations.SerializedName

data class PostmonDTO(
    @SerializedName("logradouro") val logradouro: String,
    @SerializedName("bairro") val bairro: String,
    @SerializedName("cidade") val cidade: String,
    @SerializedName("estado_info") val estadoInfo: EstadoInfoDTO
)