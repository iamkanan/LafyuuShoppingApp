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
import com.kanan.lafyu.data.adapters.NotificationOfferRecyclerViewAdapter
import com.kanan.lafyu.databinding.FragmentNotificationBinding
import com.kanan.lafyu.databinding.FragmentNotificationOfferBinding
import com.kanan.lafyu.databinding.NotificationOfferListItemBinding
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class NotificationOfferFragment : Fragment() {

    private var _notificationOfferBinding: FragmentNotificationOfferBinding? = null
    private val notificationOfferBinding get() = _notificationOfferBinding!!

    private val notificationOfferViewModel: NotificationOfferViewModel by viewModels()

    private val notificationOfferAdapter = NotificationOfferRecyclerViewAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _notificationOfferBinding = FragmentNotificationOfferBinding.inflate(inflater,container,false)
        return notificationOfferBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationOfferBinding.apply {
            notificationOfferRV.adapter = notificationOfferAdapter

            notificationOfferToolbar.setNavigationOnClickListener(View.OnClickListener {
                activity?.onBackPressed()
            })
        }

        lifecycleScope.launchWhenStarted {
            notificationOfferViewModel.data.collectLatest {
                when(it) {
                    is Resource.Success -> {
                        notificationOfferAdapter.setList(it.data)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _notificationOfferBinding = null
        super.onDestroyView()
    }
}