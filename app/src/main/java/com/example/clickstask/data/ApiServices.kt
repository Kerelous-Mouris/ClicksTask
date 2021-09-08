package com.example.clickstask.data

import com.example.clickstask.pojo.NewsResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface ApiServices {
    //preparing the API
    @GET("/v2/top-headlines")
    fun getNews(
        @Query("country") country: String = "eg",
        @Query("apiKey") apiKey:String = "63b1f94dad044add871d1e319c630265"
    ): Call<NewsResponse>

}