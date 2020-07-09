package com.huyhieu.animationtransaction

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = ArrayList<CharacterModel>()

        list.add(CharacterModel(R.drawable.vayne, "Vayne", getString(R.string._vayne_1), getString(R.string._vayne)))
        list.add(CharacterModel(R.drawable.thresh, "Thresh", getString(R.string._thresh_1), getString(R.string._thresh)))
        list.add(CharacterModel(R.drawable.yasuo, "Yasuo", getString(R.string._yasuo_1), getString(R.string._yasuo)))

        val adapter = ItemAdapter(list)
        rcv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        rcv.setHasFixedSize(true)
        rcv.adapter = adapter
    }
}