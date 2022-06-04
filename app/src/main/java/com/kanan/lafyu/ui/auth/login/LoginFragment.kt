package com.kanan.lafyu.ui.auth.login
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kanan.lafyu.R
import com.kanan.lafyu.data.models.authResponse.LoginResponseModel
import com.kanan.lafyu.data.repository.UserDataRepository
import com.kanan.lafyu.databinding.FragmentLoginBinding
import com.kanan.lafyu.ui.auth.viewmodel.AuthViewModel
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment() {


    @Inject
    lateinit var userDataRepository: UserDataRepository
    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var loginBinding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginBinding = FragmentLoginBinding.inflate(inflater,container,false)
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBinding.apply {

            dontAccountText.setOnClickListener {
                activity?.let {
                    findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                }
            }

            val register: Spannable = SpannableString("Register")
            register.setSpan(ForegroundColorSpan(Color.BLUE), 0, register.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            dontAccountText.append(register)

            myViewModel = authViewModel

        }


        lifecycleScope.launchWhenStarted {
            authViewModel.data.collectLatest { data ->
                when(data){
                    is Resource.Loading -> showLoading()
                    is Resource.Error -> {
                        hideLoading()
                        showError(data.message)
                    }
                    is Resource.Success -> {
                        saveUserData(data.data)
                        navigateToMain()
                    }
                }
            }
        }


    }

    private fun saveUserData(loginResponseModel: LoginResponseModel){
        userDataRepository.setToken(loginResponseModel.token)
    }

    private fun navigateToMain(){
        findNavController().navigate(R.id.action_to_main)
    }

    private fun showLoading(){
        loginBinding.loading.visibility = View.VISIBLE
    }

    private fun showError(message: String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun hideLoading(){
        loginBinding.loading.visibility = View.GONE
    }


}