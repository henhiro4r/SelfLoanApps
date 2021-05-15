package com.example.selfloanapps.ui.account

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.FragmentAccountBinding
import com.example.selfloanapps.ui.MainActivity
import com.example.selfloanapps.ui.viewModel.SelfLoanViewModel
import com.example.selfloanapps.utils.MessageRequestUIState
import com.example.selfloanapps.utils.PrefsManagerHelper
import com.example.selfloanapps.utils.Resource

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
            viewModel.blockCard(helper?.getAccessToken().toString())
        }

        viewModel.messageRequestUIState.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    Log.e(TAG, "onViewCreated: ${response.data?.message}")
                    if (response.data?.message.equals("logged out")) {
                        helper?.clearPref()
                        val action = AccountFragmentDirections.actionNavAccountToSplashFragment()
                        findNavController().navigate(action)
                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                    }
                    Toast.makeText(
                        activity,
                        response.data?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Resource.Error -> {
                    Log.e(TAG, "onViewCreated: ${response.message}")
                    Toast.makeText(
                        activity,
                        "An error occurred ${response.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Resource.Loading -> {

                }
                else -> Unit
            }
        })
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
            viewModel.logout(helper?.getAccessToken().toString())
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.show()
    }
}