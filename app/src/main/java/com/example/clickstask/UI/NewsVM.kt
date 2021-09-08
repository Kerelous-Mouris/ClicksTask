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

    //live data to hold the data retrieved from server
    var newsMutableLiveData: MutableLiveData<List<NewsModel>> = MutableLiveData()
    //context provided for Toast.
    lateinit var context: Context

    fun initialize(context: Context){
        this.context = context
    }
    //ask the retrofit client to retrieve data
    fun getNews(){
        NewsClient.fetchNews(
            ::onNewsFetched,
            ::onError
        )
    }
    // if any error occurred during retrieving data, notify the user with the problem
    private fun onError(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }
    //when the data retrieved successfully, add them to the liveData to observe
    private fun onNewsFetched(mutableList: List<NewsModel>) {
      newsMutableLiveData.value = mutableList
    }

}