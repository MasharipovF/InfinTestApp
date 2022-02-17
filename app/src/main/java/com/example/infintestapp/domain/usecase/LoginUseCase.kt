package com.example.infintestapp.domain.usecase

import com.example.infintestapp.domain.models.AccessToken
import com.example.infintestapp.domain.repository.LoginRepository

class LoginUseCase(private val loginRepository: LoginRepository) {

    suspend fun getAccessToken(clientId: String, clientSecret: String, code: String): String? {
        val response = loginRepository.getAccessToken(clientId,clientSecret, code)

        return if (response is AccessToken)
            response.accessToken
        else null
    }
}