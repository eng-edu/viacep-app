package com.example.viacepapp.domain.util

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
    data class Error(val error: String, val tryAgain: (() -> Unit)? = null) : ViewState<Nothing>()
}