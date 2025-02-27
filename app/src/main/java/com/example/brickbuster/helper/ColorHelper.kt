package com.example.brickbuster.helper

import android.content.Context
import android.graphics.Paint
import androidx.core.content.ContextCompat

class ColorHelper {
    companion object {
        fun getColor(context: Context, colorCode: Int): Paint {
            val paint = Paint()
            val color = ContextCompat.getColor(context, colorCode)
            paint.setColor(color)
            return paint
        }
    }
}