package com.huyhieu.animationtransaction.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.huyhieu.animationtransaction.R
import com.huyhieu.animationtransaction.adapters.SliderAdapter
import com.huyhieu.animationtransaction.models.CharacterModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_view_pager2.*
import java.util.concurrent.TimeUnit


class CustomRecyclerViewActivity : AppCompatActivity() {
    private val list = ArrayList<CharacterModel>()
    private var position = 0
    private var index = 0
    private var dispose: Disposable? = null
    
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)
        
        list.add(
            CharacterModel(
                R.drawable.vayne,
                "Vayne",
                getString(R.string._vayne_1),
                getString(R.string._vayne)
            )
        )
        list.add(
            CharacterModel(
                R.drawable.thresh,
                "Thresh",
                getString(R.string._thresh_1),
                getString(R.string._thresh)
            )
        )
        list.add(
            CharacterModel(
                R.drawable.yasuo,
                "Yasuo",
                getString(R.string._yasuo_1),
                getString(R.string._yasuo)
            )
        )
        
        
        val annotation = AnimationUtils.loadAnimation(this, R.anim.rotate_repeatcount)
        val adapter =
            SliderAdapter(list, annotation, object : SliderAdapter.SliderAdapterInterface {
                override fun onViewAttachedToWindow(adapterPosition: Int) {
                    position = adapterPosition
                }
            })
        //vpImage.adapter = adapter
        rcv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rcv.setHasFixedSize(true)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rcv)
        rcv.adapter = adapter
        autoScrollBanner()
    }
    
    private fun autoScrollBanner() {
        try {
            dispose?.dispose()
            dispose = Flowable.interval(5, 5, TimeUnit.SECONDS)
                .map {
                    index = if (position < list.size) {
                        position++
                    } else {
                        0
                    }
                    index
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { this.handleUIScroll() }
        } catch (e: Exception) {
        }
    }
    
    private fun handleUIScroll() {
        rcv?.smoothScrollToPosition(index)
    }
    
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
