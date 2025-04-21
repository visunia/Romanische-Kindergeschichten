package com.kindergeschichten.romanisch.ui.adapter

import android.app.Activity
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kindergeschichten.romanisch.data.Story
import com.kindergeschichten.romanisch.databinding.ItemStoryBinding
import com.us.babyeducation.assets.AssetsManager


class StoryAdapter(
    val context: Activity,
    private val storyType: String,
    private val onItemClick: (item: Story) -> Unit
) : ListAdapter<Story, StoryAdapter.MyViewHolder>(RecipeDiffCallback()) {

    val assetManager = AssetsManager.getInstance(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }

    inner class MyViewHolder(val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Story) {
            binding.imgInfo.setOnClickListener {
                onItemClick(item)
            }

            binding.tvTitle.text = item.getStoryName(storyType)
            binding.tvDesciption.text = item.getStoryIntroText(storyType)

            assetManager.getPicture(item.storyPicture)?.apply {
                binding.imgStoryImage.setImageBitmap(BitmapFactory.decodeStream(this))
            }

        }
    }

    // DiffUtil for efficient updates
    class RecipeDiffCallback : DiffUtil.ItemCallback<Story>() {
        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem.storyId == newItem.storyId
        }

        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem == newItem
        }
    }
}