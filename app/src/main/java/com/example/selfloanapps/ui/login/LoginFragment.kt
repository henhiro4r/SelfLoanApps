package com.example.selfloanapps.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.FragmentLoginBinding
import com.example.selfloanapps.ui.MainActivity
import com.example.selfloanapps.utils.LoginUiState
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    lateinit var viewModel: LoginViewModel
    private var email: String = ""
    private var password: String = ""
    private val TAG = "LoginFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        viewModel = (activity as MainActivity).loginViewModel

        binding.tlEmail.editText?.addTextChangedListener { editable ->
            if (editable.toString().isNotEmpty()) {
                email = editable.toString().trim()
            }
        }

        binding.tlPassword.editText?.addTextChangedListener { editable ->
            if (editable.toString().isNotEmpty()) {
                password = editable.toString().trim()
            }
        }

        binding.btnLogin.setOnClickListener {
            getFCMToken()
            if (email.isEmpty() && password.isEmpty()) {
                Toast.makeText(activity, "Please input email and password", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(email, password)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.loginUiState.collect {
                when(it) {
                    is LoginUiState.Success -> {
                        isLoading(false)
                        val action = LoginFragmentDirections.actionLoginFragmentToNavActiveLoan()
                        findNavController().navigate(action)
                    }

                    is LoginUiState.Error -> {
                        isLoading(false)
                        Log.e(TAG, "onViewCreated: ${it.message}")
                        Toast.makeText(activity, "An error occurred ${it.message}", Toast.LENGTH_LONG).show()
                    }

                    is LoginUiState.Loading -> {
                        isLoading(true)
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Log.d(TAG, msg)
            Log.d(TAG, "token: $token")
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        })
    }

    private fun isLoading(loading: Boolean) {
        when(loading) {
            true -> {
                binding.btnLogin.visibility = View.INVISIBLE
                binding.pbLogin.visibility = View.VISIBLE
            }
            false -> {
                binding.pbLogin.visibility = View.INVISIBLE
                binding.btnLogin.visibility = View.VISIBLE
            }
        }
    }
}