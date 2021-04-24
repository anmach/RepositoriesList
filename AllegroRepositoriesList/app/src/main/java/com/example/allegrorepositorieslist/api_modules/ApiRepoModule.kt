package com.example.allegrorepositorieslist.api_modules

import android.util.Log
import com.example.allegrorepositorieslist.model.RepoBaseModel
import com.example.allegrorepositorieslist.model.RepoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

fun runRepoService(repoName: String, onResponeGot: (RepoModel) -> Unit){
    getRepoService().getRepo(repoName).enqueue(object : Callback<RepoModel> {
        override fun onFailure(call: Call<RepoModel>, t: Throwable) {
            Log.d("REPO_SERVICE", "An error happened!")
            t.printStackTrace()
        }

        override fun onResponse(call: Call<RepoModel>, response: Response<RepoModel>) {
            if (response.body() != null) {
                onResponeGot(response.body()!!)
                Log.d("REPO_SERVICE", response.body().toString())
            }
            else{
                Log.d("REPO_SERVICE", "Got nothing in response")
            }
        }
    })
}

fun getRepoService(): RepoService = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
    .create(RepoService::class.java)

interface RepoService {
    @GET("/repos/allegro/{repo}")
    fun getRepo(@Path("repo") repo: String): Call<RepoModel>
}

