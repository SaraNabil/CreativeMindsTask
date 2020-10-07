package com.example.creativemindstask.Network

import com.example.creativemindstask.Model.RepositoriesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointInterface {
    @GET("repos")
    fun getListOfRepositories(@Query("page") page:Int,
                  @Query("per_page") perPage:Int,
                  @Query("access_token") accessToken:String): Call<List<RepositoriesModel>?>

}