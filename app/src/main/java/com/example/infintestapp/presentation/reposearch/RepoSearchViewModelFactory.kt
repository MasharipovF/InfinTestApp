package com.example.infintestapp.presentation.reposearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.infintestapp.domain.usecase.GithubRepositoriesUseCase

class RepoSearchViewModelFactory(val gitReposUseCase: GithubRepositoriesUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return RepoSearchViewModel(
            githubRepositoriesUseCase = gitReposUseCase
        ) as T
    }
}