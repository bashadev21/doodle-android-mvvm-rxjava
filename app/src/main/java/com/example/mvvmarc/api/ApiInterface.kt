package com.example.mvvmarc.api

import com.example.mvvmarc.model.JokesModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("get_memes")
    suspend  fun getMemes():Response<JokesModel>
    @GET("get_memes")
      fun getJokes():Observable<JokesModel>
}