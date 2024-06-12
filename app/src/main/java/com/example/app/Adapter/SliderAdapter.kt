package com.example.app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.app.Model.SliderModel
import com.example.app.R

class SliderAdapter(
    private var sliderItems: List<SliderModel>, // Исправлено: Тип должен быть List<SliderModel>
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slider_item_container, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(sliderItems[position], context) // Исправлено: Передаем конкретный элемент списка
        if (position == sliderItems.lastIndex - 1) {
            viewPager2.post {
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int = sliderItems.size

    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageSlide)

        fun setImage(sliderItem: SliderModel, context: Context) { // Исправлено: Используем SliderModel, а не List<SliderModel>
            val requestOptions = RequestOptions().transform(CenterInside())

            Glide.with(context)
                .load(sliderItem.url)
                .apply(requestOptions)
                .into(imageView)
        }
    }
}
