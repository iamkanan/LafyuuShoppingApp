package com.kanan.lafyu.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kanan.lafyu.data.adapters.AllCategoryRecyclerViewAdapter
import com.kanan.lafyu.databinding.FragmentCategoryBinding
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private var _categoryBinding: FragmentCategoryBinding? = null
    private val categoryBinding get() = _categoryBinding!!

    private val categoryViewModel: CategoryViewModel by viewModels()

    private val allCategoryAdapter = AllCategoryRecyclerViewAdapter()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _categoryBinding = FragmentCategoryBinding.inflate(inflater,container,false)
        return categoryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryBinding.apply {
            allCategoryRV.adapter = allCategoryAdapter
            categoryToolbar.setNavigationOnClickListener(View.OnClickListener {
                activity?.onBackPressed()
            })
        }

        lifecycleScope.launchWhenStarted {
            categoryViewModel.data.collectLatest {
                when(it) {
                    is Resource.Success -> {
                        allCategoryAdapter.setList(it.data)
                    }
                }
            }
        }


    }


}