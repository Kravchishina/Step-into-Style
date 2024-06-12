package com.example.app.Adapter

import  com.example.app.Model.BrandModel
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.View
import com.example.app.databinding.ViewholderBrandBinding
import android.content.Context
import android.content.res.ColorStateList
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.example.app.R
import android.view.LayoutInflater

class BrandAdapter(val items: MutableList<BrandModel>):
    RecyclerView.Adapter<BrandAdapter.Viewholder>(){

        private var selectedPosition= -1
        private var lastSelectedPosition = -1
        private lateinit var context: Context

    class Viewholder (val binding: ViewholderBrandBinding):
        RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderBrandBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: BrandAdapter.Viewholder, position: Int) {
         val item = items [position]
        holder.binding.title.text=item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
       holder.binding.title.setTextColor(context.resources.getColor(R.color.white))
        if(selectedPosition==position){
            holder.binding.pic.setBackgroundColor(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.purple_bg)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(R.color.white))
            )

            holder.binding.title.visibility =View.VISIBLE
        }else{
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(R.color.black))
            )

            holder.binding.title.visibility =View.GONE
        }
    }

    override fun getItemCount(): Int = items.size
    }
