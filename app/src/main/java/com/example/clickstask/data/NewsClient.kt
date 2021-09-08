package com.example.clickstask.data


import com.example.clickstask.pojo.NewsModel
import com.example.clickstask.pojo.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsClient {

    private val services: ApiServices
    private const val BASE_URL = "http://newsapi.org/"


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        services = retrofit.create(ApiServices::class.java)
    }

    //function responsible to hit the API and retrieve response
    fun fetchNews(
        onSuccess:(newsList:MutableList<NewsModel>)->Unit,
        onError:(message:String)-> Unit
    ){
        services.getNews("eg","63b1f94dad044add871d1e319c630265").enqueue(object : Callback<NewsResponse>{
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        //handle the successful response
                        onSuccess.invoke(response.body()!!.articles)
                    }else{
                        onError.invoke("body is null")
                    }
                }
            }
            //handle response Failure
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                println(t.message.toString())
                onError.invoke(t.message.toString())
            }

        })

    }

}