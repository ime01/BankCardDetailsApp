package com.flowz.bankcarddetailsapp.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.flowz.bankcarddetailsapp.R
import com.flowz.bankcarddetailsapp.network.ApiServiceCall
import com.flowz.bankcarddetailsapp.network.CardApiClient
import com.flowz.bankcarddetailsapp.repository.CardRepository
import com.flowz.printfuljobtask.utils.getConnectionType
import com.flowz.printfuljobtask.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_card_details.*



@AndroidEntryPoint
class CardDetailsFragment : Fragment() {

    private val cardViewModel by viewModels<CardViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        search_button.setOnClickListener {

            if (TextUtils.isEmpty(entered_numbers.text.toString())){
                entered_numbers.setError(getString(R.string.no_digits))
                return@setOnClickListener
            } else{
                val query = entered_numbers.text.toString()

                if (getConnectionType(requireContext())){

                    cardViewModel.setUpSearchCall(query)
                    cardViewModel.cardNetworkstatus.observe(viewLifecycleOwner, Observer {state->

                        state?.also {
                            when (it){
                                CardApiStatus.ERROR->{
                                    showSnackbar(card_scheme, getString(R.string.not_found))
                                    progress_bar.visibility = View.GONE
                                }
                                CardApiStatus.LOADING->{
                                    progress_bar.visibility = View.VISIBLE
                                    card_scheme.visibility = View.GONE
                                    card_type.visibility = View.GONE
                                    bank.visibility = View.GONE
                                    country.visibility = View.GONE
                                    prepaid_or_postpaid.visibility = View.GONE
                                    card_number_length.visibility = View.GONE
                                    user_image_placeholder.visibility = View.GONE
                                }
                                CardApiStatus.DONE->{
                                    progress_bar.visibility = View.GONE
                                    card_scheme.visibility =  View.VISIBLE
                                    card_type.visibility =  View.VISIBLE
                                    bank.visibility =  View.VISIBLE
                                    country.visibility =  View.VISIBLE
                                    prepaid_or_postpaid.visibility = View.VISIBLE
                                    card_number_length.visibility =  View.VISIBLE
                                    user_image_placeholder.visibility =  View.VISIBLE
                                    showCardData()
                                }
                            }
                        }
                    })

                }else{
                    AlertDialog.Builder(this.requireContext()).setTitle(getString(R.string.no_internet))
                        .setMessage(getString(R.string.check_connection))
                        .setPositiveButton(getString(R.string.ok)) { _, _ -> }
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                }
            }
        }
    }


    fun showCardData(){

        cardViewModel.cardInfoFromNetwork.observe(requireActivity(), Observer {cardDetails->

            card_scheme.setText(getString(R.string.cardscheme) + cardDetails?.scheme)
            card_type.setText(getString(R.string.cardtype) + cardDetails?.brand)
            bank.setText(getString(R.string.bank_name) + cardDetails.bank?.name)
            country.setText(getString(R.string.country) + cardDetails.country?.name)
            card_number_length.setText(getString(R.string.num_length) + cardDetails.number?.length.toString())

            prepaid_or_postpaid.apply {
                if (cardDetails?.prepaid == true){
                   text = getString(R.string.prepaid_yes)
                }else{
                    if (cardDetails?.prepaid == false){
                        text = getString(R.string.prepaid_no)
                    }
                }
            }
        })

    }

}