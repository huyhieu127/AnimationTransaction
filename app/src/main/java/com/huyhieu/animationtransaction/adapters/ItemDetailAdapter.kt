package com.huyhieu.animationtransaction.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.huyhieu.animationtransaction.models.CharacterModel
import com.huyhieu.animationtransaction.activities.DetailActivity
import com.huyhieu.animationtransaction.R

class ItemDetailAdapter(private val data: ArrayList<CharacterModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_detail, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val characterModel = data[position]
        (holder as MyViewHolder).fillData(characterModel)
    }

    internal inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var cvImg = itemView.findViewById<CardView>(R.id.cvImg)
        private var img = itemView.findViewById<ImageView>(R.id.imgAvatar)
        private var name = itemView.findViewById<TextView>(R.id.txtName)
        private var nickName = itemView.findViewById<TextView>(R.id.txtNickName)

        fun fillData(characterModel: CharacterModel) {
            img.setImageResource(characterModel.img)
            name.text = characterModel.name
            nickName.text = characterModel.nickName

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("model", characterModel)
                itemView.context.startActivity(intent)
                (itemView.context as Activity).finish()
                (itemView.context as Activity).overridePendingTransition(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
            }
        }
    }
}