package com.huyhieu.animationtransaction.utils

import android.app.Activity
import android.graphics.BitmapFactory
import androidx.annotation.FloatRange
import androidx.annotation.IdRes
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory

fun Activity.radiusRounded(
    @IdRes idDrawable: Int,
    @FloatRange cornerRadius: Float
): RoundedBitmapDrawable {
    val bitmap = BitmapFactory.decodeResource(this.resources, idDrawable)
    val round = RoundedBitmapDrawableFactory.create(this.resources, bitmap)
    round.cornerRadius = cornerRadius
    return round
}

fun Activity.circleImage(
    @IdRes idDrawable: Int
): RoundedBitmapDrawable {
    val bitmap = BitmapFactory.decodeResource(this.resources, idDrawable)
    val round = RoundedBitmapDrawableFactory.create(this.resources, bitmap)
    round.isCircular = true
    return round
}