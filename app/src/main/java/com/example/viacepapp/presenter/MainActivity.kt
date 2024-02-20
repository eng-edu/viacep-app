package com.example.viacepapp.presenter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.viacepapp.presenter.util.ViewState
import com.example.viacepapp.presenter.theme.ViaCepAppTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViaCepAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun Greeting(
        modifier: Modifier = Modifier,
        viewModel: AddressViewModel = getViewModel()
    ) {

        lifecycleScope.launch {

            viewModel.addressState.collect {
                when (it) {
                    is ViewState.Loading -> {
                        Log.i("TESTEDU", "LOADING")
                    }

                    is ViewState.Success -> {
                        Log.i("TESTEDU", it.data.toString())
                    }

                    is ViewState.Error -> {
                        Log.i("TESTEDU", it.error)
                    }
                }
            }
        }

        viewModel.getAddress("68459360")

        Text(text = "Testando")
    }
}

