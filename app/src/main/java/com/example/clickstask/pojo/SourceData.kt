package com.example.clickstask.pojo

import com.google.gson.annotations.SerializedName

data class SourceData (
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
        )