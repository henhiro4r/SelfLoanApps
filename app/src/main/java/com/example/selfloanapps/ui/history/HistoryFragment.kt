package com.example.selfloanapps.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.FragmentHistoryBinding
import com.example.selfloanapps.ui.MainActivity
import com.example.selfloanapps.ui.viewModel.SelfLoanViewModel

class HistoryFragment : Fragment(R.layout.fragment_history) {

    lateinit var viewModel: SelfLoanViewModel
    lateinit var historyAdapter: HistoryAdapter
    private val TAG = "HistoryFragment"
    private lateinit var binding: FragmentHistoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHistoryBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        historyAdapter.setOnItemClickListener {
            val action = HistoryFragmentDirections.actionNavHistoryToLoanDetailFragment(it)
            findNavController().navigate(action)
        }

        // TODO: DO API CALL HERE
    }

    private fun isLoading(loading: Boolean) {
        when(loading) {
            true -> {

            }
            false -> {

            }
        }
    }

    private fun setupRecyclerView() {
        historyAdapter = HistoryAdapter()
    }
}