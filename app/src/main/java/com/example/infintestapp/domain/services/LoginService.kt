package com.example.infintestapp.domain.services

import com.example.infintestapp.domain.models.AccessToken
import retrofit2.Response
import retrofit2.http.*

interface LoginService {


    @Headers("Accept: application/json")
    @POST ("login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
    @Field("client_id") clientId: String,
    @Field("client_secret") clientSecret: String,
    @Field("code") code: String,
    ): Response<AccessToken>


}