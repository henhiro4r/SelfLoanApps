package com.example.selfloanapps.ui.account.taps

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.FragmentTapHistoryBinding
import com.example.selfloanapps.ui.MainActivity
import com.example.selfloanapps.ui.viewModel.SelfLoanViewModel
import com.example.selfloanapps.utils.PrefsManagerHelper
import com.example.selfloanapps.utils.Resource

class TapHistoryFragment : Fragment(R.layout.fragment_tap_history) {

    lateinit var viewModel: SelfLoanViewModel
    lateinit var tapAdapter: TapAdapter
    private val TAG = "TapHistoryFragment"
    private lateinit var binding: FragmentTapHistoryBinding
    private var helper: PrefsManagerHelper? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTapHistoryBinding.bind(view)

        helper = PrefsManagerHelper(requireActivity())
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.getTapHistory(helper?.getAccessToken().toString())

        viewModel.tapHistory.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    isLoading(false)
                    response.data?.let {
                        Log.d(TAG, "onViewCreated: ${it.tapContainer[0].id}")
                        tapAdapter.differ.submitList(it.tapContainer[0].tapHistory)
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
        when (loading) {
            true -> {
                binding.pbTapHistory.visibility = View.VISIBLE
            }
            false -> {
                binding.pbTapHistory.visibility = View.INVISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        tapAdapter = TapAdapter()
        binding.rvTapHistory.apply {
            adapter = tapAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}