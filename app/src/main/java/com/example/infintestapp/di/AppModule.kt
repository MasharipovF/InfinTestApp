package com.example.infintestapp.di

import com.example.infintestapp.domain.usecase.GithubRepositoriesUseCase
import com.example.infintestapp.domain.usecase.LoginUseCase
import com.example.infintestapp.presentation.main.MainActivityViewModelFactory
import com.example.infintestapp.presentation.reposearch.RepoSearchViewModel
import com.example.infintestapp.presentation.reposearch.RepoSearchViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {


    @Provides
    fun provideMainViewModelFactory(
        loginUseCase: LoginUseCase
    ): MainActivityViewModelFactory {
        return MainActivityViewModelFactory(loginUseCase = loginUseCase)
    }

    @Provides
    fun provideRepoSearchViewModelFactory(
        gitReposUseCase: GithubRepositoriesUseCase
    ): RepoSearchViewModelFactory {
        return RepoSearchViewModelFactory(gitReposUseCase = gitReposUseCase)
    }

}