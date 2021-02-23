package com.flowz.bankcarddetailsapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CardApiClient {

    companion object {
        const val BASE_URL = "https://lookup.binlist.net"
    }

}