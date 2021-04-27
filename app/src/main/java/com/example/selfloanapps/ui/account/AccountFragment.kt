package com.example.selfloanapps.ui.account

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.FragmentAccountBinding
import com.example.selfloanapps.databinding.FragmentLoginBinding
import com.example.selfloanapps.ui.MainActivity
import com.example.selfloanapps.ui.history.HistoryFragmentDirections
import com.example.selfloanapps.ui.login.LoginFragmentDirections
import com.example.selfloanapps.ui.viewModel.SelfLoanViewModel
import com.example.selfloanapps.utils.LoginUiState
import com.example.selfloanapps.utils.MessageRequestUIState
import com.example.selfloanapps.utils.PrefsManagerHelper
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.coroutines.flow.collect

class AccountFragment : Fragment(R.layout.fragment_account) {

    lateinit var viewModel: SelfLoanViewModel
    private lateinit var binding: FragmentAccountBinding
    private val TAG = "AccountFragment"
    private var helper: PrefsManagerHelper? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        helper = PrefsManagerHelper(requireActivity())
        setupUI()

        binding.btnLogout.setOnClickListener {
            showConfirmation(view)
        }

        binding.ibTapHistory.setOnClickListener {
            val action = AccountFragmentDirections.actionNavAccountToTapHistoryFragment()
            findNavController().navigate(action)
        }

        binding.ibBlock.setOnClickListener {
            viewModel.blockCard()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.messageRequestUIState.collect{
                when (it) {
                    is MessageRequestUIState.Success -> {
                        Log.e(TAG, "onViewCreated: ${it.response.message}")
                        if (it.response.message.equals("logged out")) {
                            helper?.clearPref()
                            val action = AccountFragmentDirections.actionNavAccountToLoginFragment()
                            findNavController().navigate(action)
                        }
                        Toast.makeText(
                            activity,
                            it.response.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    is MessageRequestUIState.Error -> {
                        Log.e(TAG, "onViewCreated: ${it.message}")
                        Toast.makeText(
                            activity,
                            "An error occurred ${it.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    is MessageRequestUIState.Loading -> {

                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setupUI() {
        binding.include.tvMemberName.text = helper?.getName().toString()
        binding.include.tvMemberMajor.text = helper?.getMajor().toString()
        binding.include.tvMemberNim.text = helper?.getNim().toString()
    }

    private fun showConfirmation(view: View) {
        val builder = AlertDialog.Builder(view.context)
        builder.setTitle("Confirmation")
        builder.setMessage("Are sure want to logout?")
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.logout()
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.show()
    }
}