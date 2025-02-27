package com.example.brickbuster.game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import com.example.brickbuster.R
import com.example.brickbuster.helper.ColorHelper

class Arena(val left: Float, val top: Float, val right: Float, val bottom: Float) : GameEntity {
    companion object {
        const val ARENA_HALF_WIDTH = 800F
        const val ARENA_HALF_HEIGHT = 350F
    }

    override var x: Float = 0F

    override var y: Float = 0F

    override var width: Float = ARENA_HALF_WIDTH * 2

    override var height: Float = ARENA_HALF_HEIGHT * 2

    override fun render(context: Context, canvas: Canvas) {
        val paint = ColorHelper.getColor(context, R.color.white)
        paint.strokeWidth = -10f
        paint.style = Paint.Style.STROKE
        canvas.drawRect(left, top, right, bottom, paint)
    }
}