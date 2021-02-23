package com.flowz.bankcarddetailsapp.models


import com.google.gson.annotations.SerializedName

data class Country(
    val alpha2: String? = null,
    val currency: String? = null,
    val emoji: String? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val name: String? = null,
    val numeric: String? = null
)