package com.huyhieu.animationtransaction

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_content_detail.*
import kotlinx.android.synthetic.main.layout_header_detail.*

class DetailActivity : AppCompatActivity() {

    private var isMain = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppThemeDark)
        setContentView(R.layout.activity_detail)

        val characterModel = intent.extras?.getParcelable<CharacterModel>("model")
        if (characterModel != null) {
            imgAvatar.setImageResource(characterModel.img)
        }
        txtName.text = characterModel?.name
        txtNickName.text = characterModel?.nickName
        txtContent.text = characterModel?.content

        isMain = intent.extras?.getBoolean("isMain",false)!!

        cvBack.setOnClickListener { onBackPressed() }

        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        animation.duration = 500
        txtContent.startAnimation(animation)

        val list = ArrayList<CharacterModel>()

        list.add(CharacterModel(R.drawable.vayne, "Vayne", getString(R.string._vayne_1), getString(R.string._vayne)))
        list.add(CharacterModel(R.drawable.thresh, "Thresh", getString(R.string._thresh_1), getString(R.string._thresh)))
        list.add(CharacterModel(R.drawable.yasuo, "Yasuo", getString(R.string._yasuo_1), getString(R.string._yasuo) ))

        list.add(CharacterModel(R.drawable.vayne, "Vayne1", getString(R.string._vayne_1), getString(R.string._vayne)))
        list.add(CharacterModel(R.drawable.thresh, "Thresh1", getString(R.string._thresh_1), getString(R.string._thresh)))
        list.add(CharacterModel(R.drawable.yasuo, "Yasuo1", getString(R.string._yasuo_1), getString(R.string._yasuo) ))

        val adapter = ItemDetailAdapter(list)
        rcvModel.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcvModel.setHasFixedSize(true)
        rcvModel.adapter = adapter
        cdlMain.setOnClickListener { motionLayoutDetail.transitionToStart() }
        nestContent.setOnScrollChangeListener { _, _, _, _, _ -> motionLayoutDetail.transitionToStart() }
    }

    override fun onBackPressed() {
        /*if (isMain) {
            finishAfterTransition()
        }else{
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }*/

        /*Animation normal*/
        finishAfterTransition()
    }
}