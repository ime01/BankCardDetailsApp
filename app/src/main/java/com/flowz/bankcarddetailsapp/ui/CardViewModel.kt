package com.flowz.bankcarddetailsapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flowz.bankcarddetailsapp.models.CardResponse
import com.flowz.bankcarddetailsapp.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class  CardApiStatus {LOADING, ERROR, DONE}

class CardViewModel @ViewModelInject constructor (private var cardRepository: CardRepository) : ViewModel(){

     val cardInfoFromNetwork = MutableLiveData<CardResponse>()

     val cardNetworkstatus = MutableLiveData<CardApiStatus>()


    fun setUpSearchCall(query: String){

        viewModelScope.launch(Dispatchers.IO ){

            try {
                withContext(Dispatchers.Main){
                    cardNetworkstatus.value = CardApiStatus.LOADING
                }

                cardInfoFromNetwork.postValue(cardRepository.SearchCardInfo(query))

                withContext(Dispatchers.Main){
                    cardNetworkstatus.value = CardApiStatus.DONE
                }

            }catch (e:Exception){
                e.printStackTrace()
                withContext(Dispatchers.Main){
                    cardNetworkstatus.value = CardApiStatus.ERROR
                }

            }


        }
    }
}