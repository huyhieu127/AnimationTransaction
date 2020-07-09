package com.huyhieu.animationtransaction

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_content_detail.*
import kotlinx.android.synthetic.main.layout_header_detail.*

class DetailActivity : AppCompatActivity() {
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

        cvBack.setOnClickListener { onBackPressed() }

        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        animation.duration = 500
        txtContent.startAnimation(animation)
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }
}