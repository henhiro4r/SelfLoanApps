package com.example.selfloanapps.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.FragmentLoanDetailBinding
import com.example.selfloanapps.models.local.Loan
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
        (activity as AppCompatActivity).supportActionBar?.title = loan.id
        setupUi(loan)
    }

    private fun setupUi(loan: Loan) {
        binding.tvLoanId.text = loan.id
        binding.tvDueDate.text = loan.dueDate
        binding.tvFineValue.text = loan.fine
        binding.tvReturnDate.text = loan.returnDate
        binding.tvLoanStatus.text = loan.status
        bookAdapter.differ.submitList(loan.book.toList())
    }

    private fun setupRecyclerView() {
        bookAdapter = LoanDetailBookAdapter()
        binding.rvBorrowedBooks.apply {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}