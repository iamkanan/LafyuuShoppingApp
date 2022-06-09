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
import com.kanan.lafyu.data.adapters.NotificationActivityRecyclerViewAdapter
import com.kanan.lafyu.databinding.FragmentNotificationActivityBinding
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class NotificationActivityFragment : Fragment() {

    private var _notificationActivityBinding: FragmentNotificationActivityBinding? = null
    private val notificationActivityBinding get() = _notificationActivityBinding!!

    private val notificationActivityViewModel: NotificationActivityViewModel by viewModels()
    private val notificationActivityAdapter = NotificationActivityRecyclerViewAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _notificationActivityBinding =
            FragmentNotificationActivityBinding.inflate(inflater, container, false)
        return notificationActivityBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notificationActivityBinding.apply {
            notificationActivityRV.adapter = notificationActivityAdapter

            notificationActivityToolbar.setNavigationOnClickListener(View.OnClickListener {
                activity?.onBackPressed()
            })
        }

        lifecycleScope.launchWhenStarted {
            notificationActivityViewModel.data.collectLatest {
                when (it) {
                    is Resource.Success -> {
                        notificationActivityAdapter.setList(it.data)
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        _notificationActivityBinding = null
        super.onDestroyView()
    }


}