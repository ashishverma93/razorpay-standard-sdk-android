package com.example.razorpayintegration.network

import com.example.razorpayintegration.repository.ApiService
import com.example.razorpayintegration.utils.AppConstants
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    private lateinit var apiService: ApiService

    fun getInstance(): ApiService {
        if (!this::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .client(getOkHttpClient())
                .build()
            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService
    }

    private fun getOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.connectTimeout(1, TimeUnit.MINUTES)
        client.writeTimeout(1, TimeUnit.MINUTES)
        client.readTimeout(1, TimeUnit.MINUTES)
        client.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .headers(original.headers())
            requestBuilder.method(original.method(), original.body())
            chain.proceed(requestBuilder.build())
        }
        return client.build()
    }

    private fun getGson(): Gson {
        val gsonBuilder = GsonBuilder().setLenient()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }
}