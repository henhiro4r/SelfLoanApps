package com.example.selfloanapps.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.selfloanapps.databinding.ItemBorrowedBookBinding
import com.example.selfloanapps.models.local.Book

class LoanDetailBookAdapter : RecyclerView.Adapter<LoanDetailBookAdapter.BookViewHolder>() {

    inner class BookViewHolder(val binding: ItemBorrowedBookBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.isbn == newItem.isbn
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            ItemBorrowedBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = differ.currentList[position]
        holder.binding.cvTvBookTitle.text = book.title
        holder.binding.cvTvPublisherName.text = book.publisherName
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}