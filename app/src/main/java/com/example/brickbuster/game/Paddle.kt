package com.example.brickbuster.game

import android.content.Context
import android.graphics.Canvas
import com.example.brickbuster.R
import com.example.brickbuster.helper.ColorHelper

class Paddle(
    override var x: Float,
    override var y: Float
): GameEntity {

    companion object {
        const val HEIGHT: Float = 20F
        const val WIDTH: Float = 260F
    }

    override var width: Float = WIDTH

    override var height: Float = HEIGHT

    override fun render(context: Context, canvas: Canvas) {
        val paint = ColorHelper.getColor(context, R.color.white)
        canvas.drawRect(x, y+HEIGHT, x+WIDTH, y, paint)
    }
}