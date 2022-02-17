package com.example.infintestapp.di

import com.example.infintestapp.Consts.API_URL
import com.example.infintestapp.Consts.BASE_URL
import com.example.infintestapp.domain.services.GithubRepositoriesService
import com.example.infintestapp.domain.services.LoginService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class ApiModule {



    @Provides
    fun provideLoginService(): LoginService {
        val retrofit: Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create()
    }



    @Provides
    fun provideGithubRepositoriesService(): GithubRepositoriesService {
        val retrofit: Retrofit =
            Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create()
    }
}