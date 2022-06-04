package com.kanan.lafyu.ui.offer

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.RoundedCornersTransformation
import com.kanan.lafyu.R
import com.kanan.lafyu.data.adapters.OfferRecyclerViewAdapter
import com.kanan.lafyu.databinding.FragmentOfferBinding
import com.kanan.lafyu.utils.Constants
import com.kanan.lafyu.utils.Resource
import com.kanan.lafyu.utils.dpToPx
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class OfferFragment() : Fragment() {

    companion object {
        const val CORNER_RADIUS = 20f
    }

    private var _offerBinding: FragmentOfferBinding? = null
    private val offerBinding get() = _offerBinding!!

    private val offerViewModel: OfferViewModel by viewModels()

    private val offerAdapter = OfferRecyclerViewAdapter()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _offerBinding = FragmentOfferBinding.inflate(inflater, container, false)
        return offerBinding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("fsdfdsfdsfdsfdsf","Offer: $arguments")

        offerAdapter.setClickListener { model ->
            Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).navigate(
                R.id.action_to_detailFragment, bundleOf(
                    Constants.PRODUCT_ID_KEY to model.productId
                ))
        }
        offerBinding.offerRV.adapter = offerAdapter

        arguments?.getInt(Constants.OFFER_PAGE_TYPE)?.let {
            offerViewModel.getOfferPage(it)
        }

        offerBinding.apply {
            offerToolbar.setNavigationOnClickListener(View.OnClickListener {
                activity?.onBackPressed()
            })
        }



        lifecycleScope.launchWhenStarted {
            offerViewModel.data.collectLatest {
                when(it) {
                    is Resource.Success -> {
                        val px = CORNER_RADIUS.dpToPx(requireContext())
                        offerBinding.offerImage.load(it.data.image) {
                            transformations(RoundedCornersTransformation(px, px, px, px))
                        }
                        offerBinding.offerTitle.text = it.data.title
                        offerAdapter.setList(it.data.products)
                    }
                }
            }
        }





    }


    override fun onDestroyView() {
        _offerBinding = null
        super.onDestroyView()
    }


}