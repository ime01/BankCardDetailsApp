package com.flowz.bankcarddetailsapp.models


import com.google.gson.annotations.SerializedName

data class Number(
    val length: Int? = null,
    val luhn: Boolean? = null
)