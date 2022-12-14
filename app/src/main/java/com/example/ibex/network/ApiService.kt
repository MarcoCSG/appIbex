package com.example.ibex.network

import com.example.ibex.network.response.LoginResponse

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @POST(value = "login")
    fun postLogin(@Query(value =  "email")email:String, @Query(value =  "password")password: String):
            retrofit2.Call<LoginResponse>

    companion object Factory{
        private const val BASE_URL = "https://www.ibexpro.com.mx/v2/src/services/login.php/?email=&password="
        fun create(): ApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}