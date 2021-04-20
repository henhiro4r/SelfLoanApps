package com.example.selfloanapps.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.FragmentLoanDetailBinding
import com.example.selfloanapps.ui.MainActivity
import com.example.selfloanapps.ui.viewModel.SelfLoanViewModel

class LoanDetailFragment : Fragment(R.layout.fragment_loan_detail) {

    lateinit var viewModel: SelfLoanViewModel
    lateinit var bookAdapter: LoanDetailBookAdapter
    val args: LoanDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentLoanDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoanDetailBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        val loan = args.loan
    }

    private fun setupRecyclerView() {
        bookAdapter = LoanDetailBookAdapter()
        binding.rvBorrowedBooks.apply {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}