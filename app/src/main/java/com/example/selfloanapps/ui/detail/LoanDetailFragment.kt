package com.example.selfloanapps.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.FragmentLoanDetailBinding
import com.example.selfloanapps.ui.MainActivity
import com.example.selfloanapps.ui.viewModel.SelfLoanViewModel

class LoanDetailFragment : Fragment(R.layout.fragment_loan_detail) {

    lateinit var viewModel: SelfLoanViewModel
    val args: LoanDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentLoanDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoanDetailBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel

        val loan = args.loan
    }
}