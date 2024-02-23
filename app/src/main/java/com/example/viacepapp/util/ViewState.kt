package com.example.viacepapp.util

sealed class ViewState<out T> {
    object Idle : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
    data class Error(val error: String, val tryAgain: (() -> Unit)? = null) : ViewState<Nothing>()
}