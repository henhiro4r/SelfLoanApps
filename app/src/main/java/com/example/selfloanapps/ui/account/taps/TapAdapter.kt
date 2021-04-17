package com.example.selfloanapps.ui.account.taps

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.selfloanapps.models.local.TapHistory

class TapAdapter : RecyclerView.Adapter<TapAdapter.TapViewHolder>() {

    inner class TapViewHolder(itemview: View): RecyclerView.ViewHolder(itemview)

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
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TapViewHolder, position: Int) {
        val tap = differ.currentList[position]
        holder.itemView.apply {
            // TODO: BIND DATA TO VIEW HERE

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}