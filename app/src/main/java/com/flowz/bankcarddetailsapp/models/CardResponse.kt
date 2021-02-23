package com.flowz.bankcarddetailsapp.models


import com.google.gson.annotations.SerializedName

data class CardResponse(
    val bank: Bank? = null,
    val brand: String? = null,
    val country: Country? = null,
    val number: Number? = null,
    val prepaid: Boolean ? = null,
    val scheme: String? = null,
    val type: String? = null
)