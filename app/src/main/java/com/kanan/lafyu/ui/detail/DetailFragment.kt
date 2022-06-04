package com.kanan.lafyu.ui.detail

import ReviewRecyclerViewAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.kanan.lafyu.R
import com.kanan.lafyu.data.adapters.DetailPageViewPagerAdapter
import com.kanan.lafyu.data.adapters.ProductSizeRecyclerViewAdapter
import com.kanan.lafyu.data.models.detailResponse.Info
import com.kanan.lafyu.databinding.FragmentDetailBinding
import com.kanan.lafyu.utils.Constants
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var detailBinding: FragmentDetailBinding? = null
    private val binding get() = detailBinding!!

    private val detailViewModel: DetailViewModel by viewModels()
    private var mAdapter: DetailPageViewPagerAdapter? = null

    private val sizeAdapter = ProductSizeRecyclerViewAdapter()
    private val reviewAdapter = ReviewRecyclerViewAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = DetailPageViewPagerAdapter()




        arguments?.getInt(Constants.PRODUCT_ID_KEY)?.let { productId ->
            detailViewModel.getDetailPage(productId)
        }

        binding.apply {
            detailViewPager.adapter = mAdapter
            detailTabLayout.setupWithViewPager(detailViewPager)
            productSizeRV.adapter = sizeAdapter
            reviewRV.adapter = reviewAdapter

            reviewProductSeeMoreText.setOnClickListener {
                val productId = arguments?.getInt(Constants.PRODUCT_ID_KEY)
                Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                    .navigate(
                        R.id.action_to_reviewFragment,
                        bundleOf(Constants.PRODUCT_ID_KEY to productId)
                    )
            }

            detailToolbar.setNavigationOnClickListener(View.OnClickListener {
                activity?.onBackPressed()
            })

        }





        lifecycleScope.launchWhenStarted {
            detailViewModel.data.collectLatest { data ->
                when (data) {
                    is Resource.Success -> {
                        data.data.info?.images?.let { mAdapter?.setList(it) }
                        data.data.sizes?.let { it1 -> sizeAdapter.setList(it1) }
                        data.data.info?.let {
                            bindViews(model = it)
                        }
                        data.data.topReview?.let {
                            reviewAdapter.setList(it)

                        }
                    }

                }
            }
        }


    }


    override fun onDestroyView() {
        binding.detailViewPager.adapter = null
        detailBinding = null
        mAdapter = null
        super.onDestroyView()
    }

    private fun bindViews(model: Info) {
        binding.apply {
            detailProductTitle.text = model.title
            detailProductPrice.text = "$" + model.price.toString()
            productSpesification.text = model.specification
        }
    }


}