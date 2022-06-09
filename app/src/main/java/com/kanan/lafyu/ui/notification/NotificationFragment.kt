package com.kanan.lafyu.ui.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.kanan.lafyu.R
import com.kanan.lafyu.databinding.FragmentNotificationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : Fragment() {
    private var _notificationBinding: FragmentNotificationBinding? = null
    private val notificationBinding get() = _notificationBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _notificationBinding = FragmentNotificationBinding.inflate(inflater,container,false)
        return notificationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationBinding.apply {
            notificationOffer.setOnClickListener {
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(R.id.action_to_notificationOfferFragment)
            }
            notificationToolbar.setNavigationOnClickListener(View.OnClickListener {
                activity?.onBackPressed()
            })

            notificationFeed.setOnClickListener {
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(R.id.action_to_notificationFeedFragment)
            }

            notificationActivity.setOnClickListener {
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(R.id.action_to_notificationActivityFragment)
            }

        }
    }


    override fun onDestroyView() {
        _notificationBinding = null
        super.onDestroyView()
    }
}