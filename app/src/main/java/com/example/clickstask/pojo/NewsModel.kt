package com.example.clickstask.pojo

import com.google.gson.annotations.SerializedName

data class NewsModel (
    @SerializedName("source") val sourceData: SourceData,
    @SerializedName("title") val title: String,
    @SerializedName("urlToImage") val imageURL: String,
    @SerializedName("description") val description: String

)