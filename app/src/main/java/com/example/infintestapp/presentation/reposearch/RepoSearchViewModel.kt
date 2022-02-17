package com.example.infintestapp.presentation.reposearch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.infintestapp.Consts
import com.example.infintestapp.Preferences
import com.example.infintestapp.domain.usecase.GithubRepositoriesUseCase
import com.example.infintestapp.helpers.LoadMore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RepoSearchViewModel(val githubRepositoriesUseCase: GithubRepositoriesUseCase) : ViewModel() {


    var listToDraw: MutableLiveData<ArrayList<Any>> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var errorLoading: MutableLiveData<Boolean> = MutableLiveData()
    var filterString: MutableLiveData<String> = MutableLiveData()

    var job: Job? = null


    fun getRepositoriesList() {
        if (job?.isActive == true) {
            job?.cancel()
        }

        if (filterString.value.isNullOrEmpty()){
            listToDraw.value = null
            loading.value = false
            return
        }

        job = GlobalScope.launch {
            loading.postValue(true)
            val listResult = ArrayList<Any>()

            val repositories = githubRepositoriesUseCase.getRepositories(
                token = "token ${Preferences.token}",
                query = filterString.value!!,
                perPage = Consts.PER_PAGE,
                pageNumber = 1
            )
            if (repositories != null) {
                val body = repositories.items
                if (body != null) {
                    listResult.addAll(body)
                    if (body.size >= Consts.PER_PAGE) listResult.add(LoadMore())
                }

            } else {
                errorLoading.postValue(true)
            }
            loading.postValue(false)
            listToDraw.postValue(listResult)
        }
    }

    fun getMoreRepositoriesList(pageNumber: Int) {
        GlobalScope.launch {
            val listResult =
                ArrayList<Any>(listToDraw.value?.filter { it !is LoadMore } ?: listOf())

            val repositories = githubRepositoriesUseCase.getRepositories(
                token = "token ${Preferences.token}",
                query = filterString.value!!,
                perPage = Consts.PER_PAGE,
                pageNumber = pageNumber
            )
            if (repositories != null) {
                val body = repositories.items
                if (body != null) {
                    listResult.addAll(body)
                    if (body.size >= Consts.PER_PAGE) listResult.add(LoadMore())
                }

            } else {
                Log.d("ERROR", repositories.toString())
                errorLoading.postValue(true)
            }
            listToDraw.postValue(listResult)

        }
    }


}