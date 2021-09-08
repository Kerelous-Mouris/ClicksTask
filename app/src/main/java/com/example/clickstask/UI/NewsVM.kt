package com.example.clickstask.UI

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clickstask.data.NewsClient
import com.example.clickstask.pojo.NewsModel
import com.example.clickstask.pojo.NewsResponse

class NewsVM(): ViewModel() {

    var newsMutableLiveData: MutableLiveData<List<NewsModel>> = MutableLiveData()
    lateinit var context: Context

    fun initialize(context: Context){
        this.context = context
    }

    fun getNews(){
        NewsClient.fetchNews(
            ::onNewsFetched,
            ::onError
        )
    }

    private fun onError(message: String) {
        Log.d("Response error", message)
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

    private fun onNewsFetched(mutableList: List<NewsModel>) {
//        Toast.makeText(context,"successful data arrived", Toast.LENGTH_LONG).show()
        newsMutableLiveData.value = mutableList
    }

}