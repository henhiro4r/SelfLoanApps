package com.example.selfloanapps.ui.account

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.FragmentAccountBinding
import com.example.selfloanapps.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment(R.layout.fragment_account) {

    private lateinit var binding: FragmentAccountBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountBinding.bind(view)

        binding.btnLogout.setOnClickListener {
            showConfirmation(view)
        }
    }

    private fun showConfirmation(view: View) {
        val builder = AlertDialog.Builder(view.context)
        builder.setTitle("Confirmation")
        builder.setMessage("Are sure want to logout?")
        builder.setPositiveButton("Yes") { _, _ ->
            Toast.makeText(view.context, "Yes", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ ->
            Toast.makeText(view.context, "No", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}