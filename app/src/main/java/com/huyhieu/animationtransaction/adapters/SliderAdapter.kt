package com.huyhieu.animationtransaction.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.huyhieu.animationtransaction.R
import com.huyhieu.animationtransaction.models.CharacterModel


class SliderAdapter(private val data: ArrayList<CharacterModel>, private var annotation: Animation, private val listener: SliderAdapterInterface) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_view_pager_2, parent, false)
        return SliderViewHolder(view)
    }
    
    override fun getItemCount(): Int {
        return data.size
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as SliderViewHolder).fillData(data[position % data.size], annotation)
    }
    
    class SliderViewHolder(itemView: View) : ViewHolder(itemView) {
        private val img = itemView.findViewById<ImageView>(R.id.imgViewPager)
        private val imgAvatar = itemView.findViewById<ImageView>(R.id.imgAvatar)
        val cvImgAvatar: CardView = itemView.findViewById(R.id.cvImgAvatar)
        
        fun fillData(
            characterModel: CharacterModel,
            annotation: Animation
        ) {
            img.setImageResource(characterModel.img)
            imgAvatar.setImageResource(characterModel.img)
        }
    }
    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        (holder as SliderViewHolder).cvImgAvatar.startAnimation(annotation)
        listener.onViewAttachedToWindow(holder.adapterPosition)
    }
    interface SliderAdapterInterface{
        fun onViewAttachedToWindow(adapterPosition: Int)
    }
}