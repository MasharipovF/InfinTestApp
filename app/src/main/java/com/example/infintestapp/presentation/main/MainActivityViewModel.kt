package com.example.infintestapp.presentation.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.infintestapp.Consts
import com.example.infintestapp.Preferences
import com.example.infintestapp.domain.services.LoginService
import com.example.infintestapp.domain.usecase.LoginUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityViewModel(val loginUseCase: LoginUseCase): ViewModel() {


    val tokenReceived: MutableLiveData<Boolean> = MutableLiveData()


    fun getAccessToken(code: String) {
        Log.d("LOGIN", "ENTERED LOGIN PAGE, CODE IS $code")

        GlobalScope.launch {
            val token = loginUseCase.getAccessToken(Consts.clientId, Consts.clientSecret, code)
            if (token != null) {
                Preferences.token = token
                tokenReceived.postValue(true)
            } else {
                tokenReceived.postValue(false)
            }
            Log.d("LOGIN", token.toString())
        }
    }}
