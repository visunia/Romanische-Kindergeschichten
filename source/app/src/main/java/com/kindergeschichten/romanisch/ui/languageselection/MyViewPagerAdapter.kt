package com.kindergeschichten.romanisch.ui.languageselection

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apk.installer.permissions.LanguageHolder
import com.kindergeschichten.romanisch.databinding.ItemLanguageBinding
import com.kindergeschichten.romanisch.tools.setDrawableByName


class MyViewPagerAdapter(context: Context, val list: List<Language>) :
    RecyclerView.Adapter<LanguageHolder>() {
    private val context: Context

    init {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageHolder {
        val binding =
            ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguageHolder, position: Int) {
        val item = list[position]
        //holder.binding.tvTitle.text = item.language
        holder.binding.imgIllus.setDrawableByName(context,item.picture)
        holder.binding.tvSubtitle.text = item.subtitle
        holder.binding.tvText.text = item.text

    }

    override fun getItemCount(): Int {
        return list.size
    }
}