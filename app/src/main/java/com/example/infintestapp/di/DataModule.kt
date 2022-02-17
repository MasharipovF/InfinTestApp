package com.example.infintestapp.di

import com.example.infintestapp.domain.repository.GithubRepositoriesRepository
import com.example.infintestapp.domain.repository.GithubRepositoriesRepositoryImpl
import com.example.infintestapp.domain.repository.LoginRepository
import com.example.infintestapp.domain.repository.LoginRepositoryImpl
import com.example.infintestapp.domain.services.GithubRepositoriesService
import com.example.infintestapp.domain.services.LoginService
import dagger.Module
import dagger.Provides


@Module
class DataModule {

    @Provides
    fun provideLoginRepository(loginService: LoginService): LoginRepository {
        return LoginRepositoryImpl(loginService = loginService)
    }

    @Provides
    fun provideGithubRepositoriesRepository(repositoriesService: GithubRepositoriesService): GithubRepositoriesRepository {
        return GithubRepositoriesRepositoryImpl(service = repositoriesService)
    }

}