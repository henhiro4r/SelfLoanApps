package com.example.selfloanapps.ui.activeLoan

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.FragmentActiveLoanBinding
import com.example.selfloanapps.ui.MainActivity
import com.example.selfloanapps.ui.history.HistoryAdapter
import com.example.selfloanapps.ui.viewModel.SelfLoanViewModel

class ActiveLoanFragment : Fragment(R.layout.fragment_active_loan) {

    lateinit var viewModel: SelfLoanViewModel
    lateinit var loanAdapter: ActiveLoanAdapter
    private val TAG = "ActiveLoanFragment"
    private lateinit var binding: FragmentActiveLoanBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActiveLoanBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        loanAdapter.setOnItemClickListener {
            val action = ActiveLoanFragmentDirections.actionNavActiveLoanToLoanDetailFragment(it)
            findNavController().navigate(action)
        }

        // TODO: DO API CALL HERE
    }

    private fun setupRecyclerView() {
        loanAdapter = ActiveLoanAdapter()
        binding.rvActiveLoan.apply {
            adapter = loanAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}