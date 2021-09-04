package com.example.twiscode.service

import com.example.twiscode.Responses
import retrofit2.Call
import retrofit2.http.POST

interface ApiInterface {
    @POST("teampsisthebest")
    fun getList(): Call<ArrayList<Responses>>
}