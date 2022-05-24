package com.kanan.lafyu.ui.auth.register

import android.os.Bundle
import android.text.Html
import android.util.Patterns
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kanan.lafyu.R
import com.kanan.lafyu.databinding.FragmentRegisterBinding
import com.kanan.lafyu.ui.auth.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(), View.OnClickListener, View.OnFocusChangeListener,
    View.OnKeyListener {
    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var registerBinding: FragmentRegisterBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        return registerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerBinding.apply {

            val text =
                "<font color=#9098B1>Have a account?</font> <font color=#40BFFF>Sign In</font>"
            haveAccountRegisterText.text = Html.fromHtml(text)
            haveAccountRegisterText.setOnClickListener {
                activity.let {
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
            }

            myViewModel = authViewModel



            personText.onFocusChangeListener = this@RegisterFragment
            mailText.onFocusChangeListener = this@RegisterFragment
            passText.onFocusChangeListener = this@RegisterFragment
            passText2.onFocusChangeListener = this@RegisterFragment

        }

    }

    private fun validateFullName(): Boolean {
        var errorMessage: String? = null
        val value: String = registerBinding.personText.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Full name is required"
        }
        if (errorMessage != null) {
            registerBinding.personLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateEmail(): Boolean {
        var errorMessage: String? = null
        val value: String = registerBinding.mailText.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Email is required"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            errorMessage = "Email adress is invalid"
        }
        if (errorMessage != null) {
            registerBinding.mailLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validatePassword(): Boolean {
        var errorMessage: String? = null
        val value: String = registerBinding.passText.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Password is required"
        } else if (value.length < 6) {
            errorMessage = " Password must be 6 characters long"
        }
        if (errorMessage != null) {
            registerBinding.passLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateConfirmPassword(): Boolean {
        var errorMessage: String? = null
        val value: String = registerBinding.passText2.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Password is required"
        } else if (value.length < 6) {
            errorMessage = " Password must be 6 characters long"
        }
        if (errorMessage != null) {
            registerBinding.passLayout2.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validatePasswordAndConfirmPassword(): Boolean {
        var errorMessage: String? = null
        val password: String = registerBinding.passText.text.toString()
        val confirmPassword: String = registerBinding.passText2.text.toString()
        if (password != confirmPassword) {
            errorMessage = "Confirm password doesn't match password "
        }
        if (errorMessage != null) {
            registerBinding.passLayout2.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }


    override fun onClick(view: View?) {

    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null) {
            when (view.id) {
                R.id.personText -> {
                    if (hasFocus) {
                        if (registerBinding.personLayout.isErrorEnabled) {
                            registerBinding.personLayout.isErrorEnabled = false
                        }
                    } else {
                        validateFullName()
                    }
                }
                R.id.mailText -> {
                    if (hasFocus) {
                        if (registerBinding.mailLayout.isErrorEnabled) {
                            registerBinding.mailLayout.isErrorEnabled = false
                        }
                    } else {
                        validateEmail()
                    }
                }
                R.id.passText -> {
                    if (hasFocus) {
                        if (registerBinding.passLayout.isErrorEnabled) {
                            registerBinding.passLayout.isErrorEnabled = false
                        }
                    } else {
                        validatePassword()
                    }
                }
                R.id.passText2 -> {
                    if (hasFocus) {
                        if (registerBinding.passLayout2.isErrorEnabled) {
                            registerBinding.passLayout2.isErrorEnabled = false
                        }
                    } else {
                        validateConfirmPassword()
                        validatePasswordAndConfirmPassword()
                    }
                }
            }
        }
    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
        return false

    }


}