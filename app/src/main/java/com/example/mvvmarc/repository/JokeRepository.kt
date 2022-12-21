package com.example.mvvmarc.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmarc.api.ApiInterface
import com.example.mvvmarc.model.JokesModel

class JokeRepository(private val apiInterface: ApiInterface) {
private val jokesLiveData=MutableLiveData<JokesModel>()
    val jokes:LiveData<JokesModel>
    get() = jokesLiveData
    suspend fun getJokes(){
        val result=apiInterface.getMemes()
        if(result.body() != null){
          jokesLiveData.postValue(result.body())
        }
    }
}