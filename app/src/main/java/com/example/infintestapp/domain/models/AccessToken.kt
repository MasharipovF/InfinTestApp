package com.example.infintestapp.domain.models


import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("access_token")
    val accessToken: String? = null,
    @SerializedName("scope")
    val scope: String? = null,
    @SerializedName("token_type")
    val tokenType: String? = null
)