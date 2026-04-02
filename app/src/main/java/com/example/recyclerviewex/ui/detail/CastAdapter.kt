package com.example.recyclerviewex.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewex.data.model.Cast
import com.example.recyclerviewex.databinding.ItemCastBinding

class CastAdapter() :
    ListAdapter<Cast, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        /**
         * Task 8.2: Tạo item layout [ItemCastBinding]
         */
        val binding = ItemCastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        /**
         * Task 8.3: Tạo [CastViewHolder] để chứa view của item
         */
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        /**
         * Task 8.4: Gọi hàm [CastViewHolder.bindData] để hiển thị dữ liệu item lên UI
         */
        (holder as CastViewHolder).bindData(item)
    }

    // DiffUtil
    class DiffCallback : DiffUtil.ItemCallback<Cast>() {

        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.castId == newItem.castId

        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }
    }
}