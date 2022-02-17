package com.example.infintestapp.domain.services

import com.example.infintestapp.domain.models.AccessToken
import com.example.infintestapp.domain.models.Repositories
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubRepositoriesService {


    @Headers("Accept: application/json")
    @GET("search/repositories")
    suspend fun getGithubRepositories(
        @Header("Authorization") token: String,
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") pageNumber: Int
    ): Response<Repositories>


}