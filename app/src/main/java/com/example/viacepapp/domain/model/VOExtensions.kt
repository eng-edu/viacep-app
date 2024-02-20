package com.example.viacepapp.domain.model

import com.example.viacepapp.data.model.OpenCepDTO
import com.example.viacepapp.data.model.PostmonDTO
import com.example.viacepapp.data.model.ViaCepDTO
import com.example.viacepapp.data.model.WidnetDTO

fun ViaCepDTO.toVo() = AddressVO("viaCep", this.logradouro, this.bairro, this.cidade, this.estado)

fun OpenCepDTO.toVo() = AddressVO("openCep", this.logradouro, this.bairro, this.cidade, this.estado)
fun PostmonDTO.toVo() =
    AddressVO("postmom", this.logradouro, this.bairro, this.cidade, this.estadoInfo.nome)

fun WidnetDTO.toVo() = AddressVO("widnet", this.logradouro, this.bairro, this.cidade, this.estado)