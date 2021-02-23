package com.flowz.bankcarddetailsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flowz.bankcarddetailsapp.repository.CardRepository

class CardViewModelFactory(private val cardRepository: CardRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CardViewModel(cardRepository) as T
    }
}