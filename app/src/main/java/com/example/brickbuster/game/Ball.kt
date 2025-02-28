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

    private var range: Double = Random.nextDouble(230.0, 320.0)

    private var radian = Math.toRadians(range)

    private val speed = 8F

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

    fun calculateRedirection(collidedObject: GameEntity, result: String) {
        var smallRandomization = Random.nextDouble( -2.0, 2.0)

        if (collidedObject is Paddle) {
            if (collidedObject.nextAction !== Paddle.STANDING) {
                if (collidedObject.lastIntensity < 0) {
                    smallRandomization -= 5.0
                }
                else {
                    smallRandomization += 5.0
                }
            }
        }

        if (GameEntity.COLLISION_HORIZONTAL === result) {
            changeDirection(180 - range + 360 + smallRandomization % 360)
        }
        else {
            changeDirection(360 - range + 360 + smallRandomization % 360)
        }

    }
}