package com.kanan.lafyu.ui.review

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kanan.lafyu.data.adapters.CommentRecyclerViewAdapter
import com.kanan.lafyu.data.adapters.StarRecyclerViewAdapter
import com.kanan.lafyu.data.models.reviewResponse.StarModel
import com.kanan.lafyu.databinding.FragmentReviewBinding
import com.kanan.lafyu.utils.Constants
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ReviewFragment : Fragment() {

    private var _reviewBinding: FragmentReviewBinding? = null
    private val reviewBinding get() = _reviewBinding!!
    private val reviewViewModel: ReviewViewModel by viewModels()
    private val starAdapter: StarRecyclerViewAdapter = StarRecyclerViewAdapter()
    private val commentAdapter: CommentRecyclerViewAdapter = CommentRecyclerViewAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _reviewBinding = FragmentReviewBinding.inflate(inflater,container,false)
        return reviewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(Constants.PRODUCT_ID_KEY)?.let { productId->
            reviewViewModel.getReviewPage(7)
        }

        reviewBinding.apply {
            starRV.adapter = starAdapter
            commentRV.adapter = commentAdapter

            reviewToolbar.setNavigationOnClickListener(View.OnClickListener {
                activity?.onBackPressed()
            })
        }


        val list = listOf(
            StarModel(1),
            StarModel(2),
            StarModel(3),
            StarModel(4),
            StarModel(5)
        )




        starAdapter.addNewItem(list)

        lifecycleScope.launchWhenStarted {
            reviewViewModel.data.collectLatest { data ->
                when(data) {
                    is Resource.Success -> {
                        Log.d("ewxqwx","before")
                        commentAdapter.setList(data.data.reviews!!)
                        Log.d("ewxqwx","after")
                    }
                    is Resource.Error -> {
                        showError(data.message)
                    }

                }
            }
        }




    }

    override fun onDestroyView() {
        _reviewBinding = null
        super.onDestroyView()
    }

    private fun showError(message: String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


}