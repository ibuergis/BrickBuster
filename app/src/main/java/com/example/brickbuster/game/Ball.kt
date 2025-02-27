package com.example.brickbuster.game

import android.content.Context
import android.graphics.Canvas
import com.example.brickbuster.R
import com.example.brickbuster.helper.ColorHelper
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class Ball(override var x: Float, override var y: Float) : GameEntity {
    companion object {
        const val RADIUS: Float = 10F
    }

    override var width: Float = RADIUS

    override var height: Float = RADIUS

    //
    var range: Double = Random.nextDouble(0.0, 360.0)

    var radian = Math.toRadians(range)

    val speed = 20F

    fun move() {
        y += speed * sin(radian).toFloat()
        x += speed * cos(radian).toFloat()
    }

    fun changeDirection(range: Double) {
        this.range = range
        radian = Math.toRadians(range)
    }

    override fun render(context: Context, canvas: Canvas) {
        val paint = ColorHelper.getColor(context, R.color.white)
        canvas.drawCircle(x, y, RADIUS, paint)
    }
}