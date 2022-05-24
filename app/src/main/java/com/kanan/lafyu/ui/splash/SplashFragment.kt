package com.kanan.lafyu.ui.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kanan.lafyu.R
import com.kanan.lafyu.data.repository.UserDataRepository
import com.kanan.lafyu.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    @Inject
    lateinit var userDataRepository: UserDataRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = userDataRepository.getToken()

        val r = Runnable {
            if (token != null){
                findNavController().navigate(R.id.action_to_main)
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }
        Handler(Looper.getMainLooper()).postDelayed(r,1000)


    }


}