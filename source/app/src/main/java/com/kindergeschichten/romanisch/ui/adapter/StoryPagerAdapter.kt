package com.kindergeschichten.romanisch.ui.adapter

import android.app.Activity
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kindergeschichten.romanisch.data.Story
import com.kindergeschichten.romanisch.databinding.ItemStoryDetailBinding
import com.us.babyeducation.assets.AssetsManager

class StoryPagerAdapter(
    val context: Activity,
    private val story: List<Story>,
    val storyType: String,
    val playListener:(Story)->Unit
) : RecyclerView.Adapter<StoryPagerAdapter.MyViewHolder>() {

    val assetManager = AssetsManager.getInstance(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemStoryDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = story[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int = story.size

    inner class MyViewHolder(val binding: ItemStoryDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Story) {
            binding.tvDesciption.text = item.getStoryText(storyType)
            assetManager.getPicture(item.storyPicture)?.apply {
                binding.imgStoryImage.setImageBitmap(BitmapFactory.decodeStream(this))
            }
            binding.imgSpeak.setOnClickListener{playListener(item)}
        }
    }
}
