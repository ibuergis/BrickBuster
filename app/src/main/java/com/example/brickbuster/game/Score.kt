package com.example.brickbuster.game

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import com.example.brickbuster.R
import com.example.brickbuster.helper.ColorHelper

class Score(override var x: Float, override var y: Float) : GameEntity {
    companion object {
        const val HEIGHT: Float = 40F
        const val WIDTH: Float = 200F
    }
    override var height: Float = HEIGHT

    override var width: Float = WIDTH

    var score: Int = 0

    @SuppressLint("DefaultLocale")
    override fun render(context: Context, canvas: Canvas) {
        val paint = ColorHelper.getColor(context, R.color.white)
        paint.textSize = 30F
        canvas.drawText("Score: " + String.format("%07d", score), x, y, paint)
    }
}