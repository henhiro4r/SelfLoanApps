package com.example.selfloanapps.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.ItemHistoryBinding
import com.example.selfloanapps.models.local.Loan

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Loan>() {
        override fun areItemsTheSame(oldItem: Loan, newItem: Loan): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Loan, newItem: Loan): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val loan = differ.currentList[position]
        holder.binding.cvTvLoanId.text = ""
        holder.binding.cvTvReturnDate.text = ""
        holder.binding.cvTvLoanStatus.text = ""
        holder.binding.cvHistory.apply {
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