package com.example.selfloanapps.ui.history

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.FragmentHistoryBinding
import com.example.selfloanapps.ui.MainActivity
import com.example.selfloanapps.ui.viewModel.SelfLoanViewModel
import com.example.selfloanapps.utils.PrefsManagerHelper
import com.example.selfloanapps.utils.Resource

class HistoryFragment : Fragment(R.layout.fragment_history) {

    lateinit var viewModel: SelfLoanViewModel
    lateinit var historyAdapter: HistoryAdapter
    private val TAG = "HistoryFragment"
    private lateinit var binding: FragmentHistoryBinding
    private var helper: PrefsManagerHelper? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHistoryBinding.bind(view)

        helper = PrefsManagerHelper(requireActivity())
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        viewModel.getHistory(helper?.getAccessToken().toString())

        historyAdapter.setOnItemClickListener {
            val action = HistoryFragmentDirections.actionNavHistoryToLoanDetailFragment(it)
            findNavController().navigate(action)
        }

        viewModel.historyLoan.observe(viewLifecycleOwner, { response ->
            when(response) {
                is Resource.Success -> {
                    isLoading(false)
                    response.data?.let {
                        historyAdapter.differ.submitList(it.loanHistories.toList())
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
                binding.pbHistory.visibility = View.VISIBLE
            }
            false -> {
                binding.pbHistory.visibility = View.INVISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        historyAdapter = HistoryAdapter()
        binding.rvHistoryLoan.apply {
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}