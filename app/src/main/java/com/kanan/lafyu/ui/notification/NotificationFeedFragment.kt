package com.kanan.lafyu.ui.notification

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kanan.lafyu.R
import com.kanan.lafyu.data.adapters.NotificationFeedRecyclerViewAdapter
import com.kanan.lafyu.databinding.FragmentNotificationFeedBinding
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class NotificationFeedFragment : Fragment() {

    private var _notificationFeedBinding: FragmentNotificationFeedBinding? = null
    private val notificationFeedBinding get() = _notificationFeedBinding!!

    private val notificationFeedViewModel: NotificationFeedViewModel by viewModels()
    private val notificationFeedAdapter = NotificationFeedRecyclerViewAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _notificationFeedBinding = FragmentNotificationFeedBinding.inflate(inflater,container,false)
        return notificationFeedBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationFeedBinding.apply {
            notificationFeedRV.adapter = notificationFeedAdapter

            notificationFeedToolbar.setNavigationOnClickListener(View.OnClickListener {
                activity?.onBackPressed()
            })
        }

        lifecycleScope.launchWhenStarted {
            notificationFeedViewModel.data.collectLatest {
                when(it) {
                    is Resource.Success -> {
                        notificationFeedAdapter.setList(it.data)
                    }
                }
            }
        }


    }

    override fun onDestroyView() {
        _notificationFeedBinding = null
        super.onDestroyView()
    }


}