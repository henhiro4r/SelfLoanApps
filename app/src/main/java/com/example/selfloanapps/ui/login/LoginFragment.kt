package com.example.selfloanapps.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.FragmentLoginBinding
import com.example.selfloanapps.ui.MainActivity
import com.example.selfloanapps.utils.LoginUiState
import kotlinx.coroutines.flow.collect

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        viewModel = (activity as MainActivity).loginViewModel

        viewModel.login("", "")

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.loginUiState.collect {
                when(it) {
                    is LoginUiState.Success -> {

                    }

                    is LoginUiState.Error -> {

                    }

                    is LoginUiState.Loading -> {

                    }
                    else -> Unit
                }
            }
        }
    }
}