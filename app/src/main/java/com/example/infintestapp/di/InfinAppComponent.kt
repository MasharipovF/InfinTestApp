package com.example.infintestapp.di

import com.example.infintestapp.presentation.main.MainActivity
import com.example.infintestapp.presentation.reposearch.RepoSearchActivity
import dagger.Component


@Component(modules = [ApiModule::class, AppModule::class, DataModule::class, DomainModule::class])
interface InfinAppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(repoSearchActivity: RepoSearchActivity)

}