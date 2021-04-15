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
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogout.setOnClickListener {
            showConfirmation(view)
        }
    }

    private fun showConfirmation(view: View) {
        val builder =  AlertDialog.Builder(view.context)
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