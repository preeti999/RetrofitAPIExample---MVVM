package com.nagarro.retrofit_api_example

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//const val BASE_URL = "https://jsonplaceholder.typicode.com"


interface ApiInterface {
    @GET("/todos")
    fun getApiResponse(): Call<JsonData> //get todos list


    //get todos item --> parameter
    //https://jsonplaceholder.typicode.com/todos/id
    @GET("/todos/{id}")
    fun getDescriptionById(@Path("id") id: Int?): Call<JsonDataItem>
}
