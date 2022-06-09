package com.kanan.lafyu.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import coil.load
import coil.transform.RoundedCornersTransformation
import com.kanan.lafyu.R
import com.kanan.lafyu.data.adapters.*
import com.kanan.lafyu.databinding.FragmentHomeBinding
import com.kanan.lafyu.utils.Constants
import com.kanan.lafyu.utils.Resource
import com.kanan.lafyu.utils.dpToPx
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        const val CORNER_RADIUS = 20f
    }

    private val categoryAdapter by lazy { CategoryRecyclerViewAdapter() }
    private val saleAdapter = FlashSaleRecyclerViewAdapter()
    private val megaAdapter = MegaSaleRecyclerViewAdapter()
    private val recommendAdapter = RecommendRecyclerViewAdapter()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private var mAdapter: HomePageViewPagerAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mAdapter = HomePageViewPagerAdapter()

        binding.apply {
            categoryRV.adapter = categoryAdapter
            flashSaleRV.adapter = saleAdapter
            megaSaleRV.adapter = megaAdapter
            viewPager.adapter = mAdapter
            recommendRV.adapter = recommendAdapter
            tabLayout.setupWithViewPager(viewPager)
        }

        lifecycleScope.launchWhenStarted {
            homeViewModel.data.collectLatest {
                when (it) {
                    is Resource.Success -> {
                        val fullList = it.data.items?.getOrNull(2)?.products
                        val filteredList = fullList?.subList(1, fullList.size)
                        val px = CORNER_RADIUS.dpToPx(requireContext())
                        binding.recomendedImage.load(fullList?.first()?.thumbnailImage) {
                            transformations(RoundedCornersTransformation(px, px, px, px))
                        }
                        mAdapter?.setList(it.data.advertisments!!)
                        categoryAdapter.setList(it.data.categories!!)
                        it.data.items?.first()?.products?.let { it1 -> saleAdapter.setList(it1) }
                        it.data.items?.get(1)?.products?.let { it1 -> megaAdapter.setList(it1) }
                        filteredList?.let { it1 -> recommendAdapter.setList(it1) }
                    }

                }
            }
        }


        binding.apply {
            seeMoreFlashSale.setOnClickListener {
                navigateToOfferPage(1)
            }
            seeMoreMegaSale.setOnClickListener { navigateToOfferPage(2) }
            moreCategoryText.setOnClickListener {
                Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                    .navigate(R.id.action_to_categoryFragment)
            }

            mainToolbar.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.notification -> {
                        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                            .navigate(R.id.action_to_notificationFragment)
                    }
                }
                true

            }


        }


    }

    private fun navigateToOfferPage(type: Int) {
        val bundle = bundleOf(
            Constants.OFFER_PAGE_TYPE to type
        )
        findNavController(
            requireActivity(),
            R.id.fragmentContainerView
        ).navigate(R.id.action_to_offerFragment, bundle)
    }


    override fun onDestroyView() {
        binding.viewPager.adapter = null
        _binding = null
        mAdapter = null
        super.onDestroyView()
    }


}