package com.example.selfloanapps.ui.activeLoan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.selfloanapps.R
import com.example.selfloanapps.models.local.Loan

class ActiveLoanAdapter : RecyclerView.Adapter<ActiveLoanAdapter.ActiveViewHolder>() {

    inner class ActiveViewHolder(itemview: View): RecyclerView.ViewHolder(itemview)

    private val differCallBack = object : DiffUtil.ItemCallback<Loan>() {
        override fun areItemsTheSame(oldItem: Loan, newItem: Loan): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Loan, newItem: Loan): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveViewHolder {
        return ActiveViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_active_loan,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ActiveViewHolder, position: Int) {
        val loan = differ.currentList[position]
        holder.itemView.apply {
            // TODO: BIND DATA TO VIEW HERE
            setOnClickListener {
                onItemClickListener?.let { it(loan) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Loan) -> Unit)? = null

    fun setOnItemClickListener(listener: (Loan) -> Unit) {
        onItemClickListener = listener
    }
}