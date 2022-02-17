package com.example.infintestapp.domain.repository

import com.example.infintestapp.domain.models.AccessToken
import com.example.infintestapp.domain.models.Repositories
import com.example.infintestapp.domain.services.GithubRepositoriesService

interface GithubRepositoriesRepository {

    suspend fun getRepositories(
        token: String,
        query: String,
        perPage: Int,
        pageNumber: Int
    ): Repositories?
}

class GithubRepositoriesRepositoryImpl(private val service: GithubRepositoriesService) :
    GithubRepositoriesRepository {
    override suspend fun getRepositories(
        token: String,
        query: String,
        perPage: Int,
        pageNumber: Int
    ): Repositories? {
        val response = service.getGithubRepositories(token, query, perPage, pageNumber)
        return if (response.isSuccessful)
            response.body()
        else
            null
    }


}