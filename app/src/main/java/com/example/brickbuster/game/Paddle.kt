package com.example.brickbuster.game

import android.content.Context
import android.graphics.Canvas
import com.example.brickbuster.R
import com.example.brickbuster.helper.ColorHelper

class Paddle(private val context: Context, x: Float, y: Float): GameEntity {

    companion object {
        const val HEIGHT: Float = 20F
        const val WIDTH: Float = 260F
    }

    override var x: Float = 0F

    override var y: Float = 0F

    init {
        this.x = x
        this.y = y
    }

    override fun render(canvas: Canvas) {
        val paint = ColorHelper.getColor(context, R.color.white)
        canvas.drawRect(x, y+HEIGHT, x+WIDTH, y, paint)
    }
}