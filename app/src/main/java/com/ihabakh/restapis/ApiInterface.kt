package com.ihabakh.restapis

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts/1")
    fun getPost(): Call<PostModel>
}