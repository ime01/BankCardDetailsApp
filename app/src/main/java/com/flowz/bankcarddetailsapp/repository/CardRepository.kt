package com.flowz.bankcarddetailsapp.repository

import com.flowz.bankcarddetailsapp.models.CardResponse
import com.flowz.bankcarddetailsapp.network.ApiServiceCall
import com.flowz.bankcarddetailsapp.network.CardApiClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardRepository @Inject constructor(private val apiClient: ApiServiceCall) {

    suspend fun SearchCardInfo(query:String):CardResponse{
        return apiClient.searchCard(query)
    }


}