package com.example.recyclerviewex.ui.detail

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewex.data.model.Cast
import com.example.recyclerviewex.databinding.ItemCastBinding

class CastViewHolder(
    val binding: ItemCastBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(cast: Cast) = binding.run {
        Glide.with(root)
            .load(cast.profilePath)
            .into(imgCast)
        txtActorName.text = cast.character
        txtRealName.text = cast.originalName
    }
}