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

    //
    var radius: Double = Random.nextDouble(0.0, 360.0)

    var radian = Math.toRadians(radius)

    val speed = 20F

    fun move() {
        y += speed * sin(radian).toFloat()
        x += speed * cos(radian).toFloat()
    }

    fun changeDirection(radius: Double) {
        this.radius = radius
        radian = Math.toRadians(radius)
    }

    override fun render(context: Context, canvas: Canvas) {
        val paint = ColorHelper.getColor(context, R.color.white)
        canvas.drawCircle(x, y, RADIUS, paint)
    }
}