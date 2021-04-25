package com.example.allegrorepositorieslist.api_modules

import android.util.Log
import com.example.allegrorepositorieslist.model.RepoBaseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

fun runReposService(onResponseGot: (List<RepoBaseModel>) -> Unit, onNoResponse: () -> Unit){
    getReposService().getRepos().enqueue(object : Callback<List<RepoBaseModel>> {
        override fun onFailure(call: Call<List<RepoBaseModel>>, t: Throwable) {
            Log.d("REPOS_SERVICE", "An error happened!")
            onNoResponse()
            t.printStackTrace()
        }

        override fun onResponse(call: Call<List<RepoBaseModel>>, response: Response<List<RepoBaseModel>>) {
            if(response.body() != null){
                onResponseGot(response.body()!!)
            }
            else{
                onNoResponse()
                Log.d("REPOS_SERVICE", "Got nothing in response")
            }
        }
    })
}

fun getReposService(): ReposService = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ReposService::class.java)

interface ReposService {
    @GET("/users/allegro/repos")
    fun getRepos(): Call<List<RepoBaseModel>>
}