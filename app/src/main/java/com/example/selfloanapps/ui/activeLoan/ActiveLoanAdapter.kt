package com.example.selfloanapps.ui.activeLoan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.selfloanapps.databinding.ItemActiveLoanBinding
import com.example.selfloanapps.models.local.Loan

class ActiveLoanAdapter : RecyclerView.Adapter<ActiveLoanAdapter.ActiveViewHolder>() {

    inner class ActiveViewHolder(val binding: ItemActiveLoanBinding) :
        RecyclerView.ViewHolder(binding.root)

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
            ItemActiveLoanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ActiveViewHolder, position: Int) {
        val loan = differ.currentList[position]
        holder.binding.cvTvLoanId.text = loan.id
        holder.binding.cvTvBookListPrev.text =
            loan.book.map { it.title }.joinToString(separator = " | ")
        holder.binding.cvTvReturnDate.text = loan.dueDate
        holder.binding.cvTvDaysLeft.text = loan.daysLeft.toString()
        holder.binding.cvActiveLoan.apply {
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