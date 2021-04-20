package com.example.selfloanapps.ui.account.taps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.selfloanapps.R
import com.example.selfloanapps.databinding.ItemTapHistoryBinding
import com.example.selfloanapps.models.local.TapHistory

class TapAdapter : RecyclerView.Adapter<TapAdapter.TapViewHolder>() {

    inner class TapViewHolder(val binding: ItemTapHistoryBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<TapHistory>() {
        override fun areItemsTheSame(oldItem: TapHistory, newItem: TapHistory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TapHistory, newItem: TapHistory): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TapViewHolder {
        return TapViewHolder(
            ItemTapHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TapViewHolder, position: Int) {
        val tap = differ.currentList[position]
        holder.binding.cvTapTimeStamp.text = tap.timestamp
        holder.binding.cvTapLoc.text = tap.location
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}