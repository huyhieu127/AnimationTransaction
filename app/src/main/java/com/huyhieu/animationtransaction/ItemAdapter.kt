package com.huyhieu.animationtransaction

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.*

class ItemAdapter(private val data: ArrayList<CharacterModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val characterModel = data[position]
        (holder as MyViewHolder).fillData(characterModel)
    }
    internal inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var cvImg = itemView.findViewById<CardView>(R.id.cvImg)
        private var img = itemView.findViewById<ImageView>(R.id.imgAvatar)
        private var name = itemView.findViewById<TextView>(R.id.txtName)
        private var nickName = itemView.findViewById<TextView>(R.id.txtNickName)

        fun fillData(characterModel: CharacterModel){
            img.setImageResource(characterModel.img)
            name.text = characterModel.name
            nickName.text = characterModel.nickName

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)

                intent.putExtra("model", characterModel)

                val p1 = Pair.create<View, String>(cvImg, "imgAvatar")
                val p2 = Pair.create<View, String>(name, "txtName")
                val p3 = Pair.create<View, String>(nickName, "txtNickName")
                val activityOptions = ActivityOptions.makeSceneTransitionAnimation(itemView.context as Activity?, p1, p2, p3)
                itemView.context.startActivity(intent, activityOptions.toBundle())
            }
        }
    }
}