package com.kindergeschichten.romanisch.ui.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kindergeschichten.romanisch.databinding.MenuLayoutBinding


class MenuAdapter(private val context: Context, private val mList: List<MenuItem>, val actionCallback:(menuAction:MenuAction)->Unit) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MenuLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]
        holder.binding.tvTitle.text = context.getString(item.title)
        holder.binding.imgDrawable.setImageResource(item.drawable)

        holder.itemView.setOnClickListener{
            actionCallback(item.menuAction)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder( val binding:MenuLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}