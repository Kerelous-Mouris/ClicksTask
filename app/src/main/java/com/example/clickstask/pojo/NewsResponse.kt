package com.example.clickstask.pojo

import com.google.gson.annotations.SerializedName

data class NewsResponse (
    @SerializedName("status") val status: String,
    @SerializedName("articles") val articles: MutableList<NewsModel>,
    @SerializedName("totalResults") val results: Int
        )