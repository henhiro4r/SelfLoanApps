package com.example.selfloanapps.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.selfloanapps.R
import com.example.selfloanapps.utils.PrefsManagerHelper

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val TAG = "SplashFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var preferenceHelper = PrefsManagerHelper(requireActivity())
        Log.d(TAG, "onViewCreated: ${preferenceHelper.getAccessToken()}")

        Handler(Looper.getMainLooper()).postDelayed({
            val action: NavDirections = if (preferenceHelper.getAccessToken().isEmpty()) {
                SplashFragmentDirections.actionSplashFragmentToLoginFragment()
            } else {
                SplashFragmentDirections.actionSplashFragmentToNavActiveLoan()
            }
            findNavController().navigate(action)
        }, 2000L)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }
}