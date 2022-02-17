package com.example.infintestapp.domain.usecase

import com.example.infintestapp.domain.models.Repositories
import com.example.infintestapp.domain.repository.GithubRepositoriesRepository

class GithubRepositoriesUseCase(private val repository: GithubRepositoriesRepository) {

    suspend fun getRepositories(token: String, query:String, perPage: Int, pageNumber: Int): Repositories? {
        val response = repository.getRepositories(token, query, perPage, pageNumber)
        return if (response is Repositories)
            response
        else null
    }
}