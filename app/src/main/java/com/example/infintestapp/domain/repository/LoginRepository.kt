package com.example.infintestapp.domain.repository

import com.example.infintestapp.domain.models.AccessToken
import com.example.infintestapp.domain.services.LoginService

interface LoginRepository {

    suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ): AccessToken?
}

class LoginRepositoryImpl(private val loginService: LoginService) : LoginRepository {
    override suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ): AccessToken? {
        val response = loginService.getAccessToken(clientId, clientSecret, code)
        return if (response.isSuccessful)
            response.body()
        else
            null
    }

}