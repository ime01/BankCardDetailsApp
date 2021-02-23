package com.flowz.bankcarddetailsapp.network

import com.flowz.bankcarddetailsapp.models.CardResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url


interface ApiServiceCall {

    @Headers("Accept-Version: 3")
    @GET
    suspend fun searchCard(@Url url: String?): CardResponse
}