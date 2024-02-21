package com.example.viacepapp.domain.model

import com.example.viacepapp.data.model.OpenCepDTO
import com.example.viacepapp.data.model.PostmonDTO
import com.example.viacepapp.data.model.ViaCepDTO
import com.example.viacepapp.data.model.WidnetDTO

fun ViaCepDTO.toVo() =
    AddressVO("VIA CEP", this.logradouro, this.bairro, this.cidade, this.estado)

fun OpenCepDTO.toVo() =
    AddressVO("OPEN CEP", this.logradouro, this.bairro, this.cidade, this.estado)

fun PostmonDTO.toVo() =
    AddressVO("POSTMOM", this.logradouro, this.bairro, this.cidade, this.estadoInfo.nome)

fun WidnetDTO.toVo() = AddressVO("WIDNET", this.logradouro, this.bairro, this.cidade, this.estado)