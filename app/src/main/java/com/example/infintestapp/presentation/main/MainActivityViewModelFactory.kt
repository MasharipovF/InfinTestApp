package com.example.infintestapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.infintestapp.domain.usecase.LoginUseCase

class MainActivityViewModelFactory(val loginUseCase: LoginUseCase) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(
            loginUseCase = loginUseCase
        ) as T
    }
}