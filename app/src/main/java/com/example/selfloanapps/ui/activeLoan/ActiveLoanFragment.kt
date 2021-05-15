package com.example.selfloanapps.ui.activeLoan

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.FragmentActiveLoanBinding
import com.example.selfloanapps.ui.MainActivity
import com.example.selfloanapps.ui.viewModel.SelfLoanViewModel
import com.example.selfloanapps.utils.PrefsManagerHelper
import com.example.selfloanapps.utils.Resource

class ActiveLoanFragment : Fragment(R.layout.fragment_active_loan) {

    lateinit var viewModel: SelfLoanViewModel
    lateinit var loanAdapter: ActiveLoanAdapter
    private val TAG = "ActiveLoanFragment"
    private lateinit var binding: FragmentActiveLoanBinding
    private var helper: PrefsManagerHelper? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActiveLoanBinding.bind(view)

        helper = PrefsManagerHelper(requireActivity())
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        viewModel.getLoan(helper?.getAccessToken().toString())

        loanAdapter.setOnItemClickListener {
            val action = ActiveLoanFragmentDirections.actionNavActiveLoanToLoanDetailFragment(it)
            findNavController().navigate(action)
        }

        viewModel.currentLoan.observe(viewLifecycleOwner, { response ->
            when(response) {
                is Resource.Success -> {
                    isLoading(false)
                    response.data?.let {
                        loanAdapter.differ.submitList(it.activeLoan.toList())
                    }
                }
                is Resource.Error -> {
                    isLoading(false)
                    response.message?.let {
                        Log.e(TAG, "onViewCreated: $it")
                        Toast.makeText(activity, "An error occurred $it", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    isLoading(true)
                }
            }
        })
    }

    private fun isLoading(loading: Boolean) {
        when(loading) {
            true -> {
                binding.pbActiveLoan.visibility = View.VISIBLE
            }
            false -> {
                binding.pbActiveLoan.visibility = View.INVISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        loanAdapter = ActiveLoanAdapter()
        binding.rvActiveLoan.apply {
            adapter = loanAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}