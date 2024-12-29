package com.hanif.nexmedistest.data.network.client

import android.util.Log
import com.google.gson.GsonBuilder
import com.hanif.nexmedistest.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkProvider {

    fun createRetrofit(): Retrofit {
        return try {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
                .build()

        } catch (e: Exception){
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }

    fun createOkHttpClient(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)

        httpBuilder
            .addInterceptor(headerInterceptor())
            .addInterceptor(getHttpLoggingInterceptor())

        return httpBuilder.build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message -> Log.d("Interceptor", message) }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun headerInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("Authorization", BuildConfig.API_KEY)
                .addHeader("accept", "application/json")
                .build()
            chain.proceed(request)
        }
    }

}