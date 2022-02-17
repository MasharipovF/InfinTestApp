package com.example.infintestapp.di

import com.example.infintestapp.domain.repository.GithubRepositoriesRepository
import com.example.infintestapp.domain.repository.LoginRepository
import com.example.infintestapp.domain.usecase.GithubRepositoriesUseCase
import com.example.infintestapp.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides


@Module
class DomainModule {

    @Provides
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
        return LoginUseCase(loginRepository = loginRepository)
    }

    @Provides
    fun provideGithubRepositoriesUseCase(repository: GithubRepositoriesRepository): GithubRepositoriesUseCase {
        return GithubRepositoriesUseCase(repository = repository)
    }
}