package com.aluisderagisch.vocabulary.other_products

import android.content.Context
import android.graphics.Paint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kindergeschichten.romanisch.databinding.ItemOtherProductBinding
import com.kindergeschichten.romanisch.tools.getSimplifiedName


class OtherProductAdapter : ListAdapter<OtherProduct, OtherProductAdapter.MyViewHolder?> {

    //
    var listener: OnItemClickListener? = null

    inner class MyViewHolder(var binding: ItemOtherProductBinding) : RecyclerView.ViewHolder(binding.root)
    val context: Context
    val locale:String
    constructor(context: Context) : super(DIFF_CALLBACK) {
        this.context = context
        val locales = context.resources.configuration.locales
        locale = locales[0].getSimplifiedName()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemOtherProductBinding.inflate( LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item: OtherProduct = getItem(position)
        holder.binding.tvTitle.paintFlags = holder.binding.tvTitle.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        holder.binding.tvTitle.text = item.getTitle(locale)
        holder.binding.tvDescription.text = item.getDescription(locale)
        Glide.with(context)
            .load(Uri.parse("file:///android_asset/other_products/icons/${item.getIcon()}"))
            .into(holder.binding.img)

        holder.binding.tvTitle.setOnClickListener{
            listener?.OnItemClicked(item)
        }

        holder.binding.img.setOnClickListener{
            listener?.OnItemClicked(item)
        }

    }


    interface OnItemClickListener {
        fun OnItemClicked(product: OtherProduct)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }


    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<OtherProduct?> =
            object : DiffUtil.ItemCallback<OtherProduct?>() {
                override fun areItemsTheSame(
                    oldItem: OtherProduct,
                    newItem: OtherProduct
                ): Boolean {
                    return oldItem.appid == newItem.appid
                }

                override fun areContentsTheSame(
                    oldItem: OtherProduct,
                    newItem: OtherProduct
                ): Boolean {
                    return true
                }
            }
    }
}
