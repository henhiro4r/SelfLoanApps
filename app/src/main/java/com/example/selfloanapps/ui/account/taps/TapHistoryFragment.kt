package com.example.selfloanapps.ui.account.taps

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.FragmentTapHistoryBinding
import com.example.selfloanapps.ui.MainActivity
import com.example.selfloanapps.ui.viewModel.SelfLoanViewModel

class TapHistoryFragment : Fragment(R.layout.fragment_tap_history) {

    lateinit var viewModel: SelfLoanViewModel
    lateinit var tapAdapter: TapAdapter
    private val TAG = "TapHistoryFragment"
    private lateinit var binding: FragmentTapHistoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTapHistoryBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        // TODO: DO API CALL HERE
    }

    private fun setupRecyclerView() {
        tapAdapter = TapAdapter()
        binding.rvTapHistory.apply {
            adapter = tapAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}