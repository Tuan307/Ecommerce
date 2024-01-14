package com.example.ecommerceappproject.data.remote_api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {
    private const val URL =
        "https://pc-shop-backend-git-doquang227-dev.apps.sandbox-m3.1530.p1.openshiftapps.com/api/v1/"
    var mToken = ""

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val builder =
        Retrofit.Builder().baseUrl(URL).addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $mToken")
                    .build()
                chain.proceed(request)
            }.build())
    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}