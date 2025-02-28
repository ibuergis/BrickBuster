package com.example.brickbuster.game

import android.content.Context
import android.graphics.Canvas
import com.example.brickbuster.R
import com.example.brickbuster.helper.ColorHelper

class Paddle(
    override var x: Float,
    override var y: Float
): GameEntity {

    var lastIntensity: Float = 0F

    private val speed: Float = 12F

    companion object {
        const val HEIGHT: Float = 20F
        const val WIDTH: Float = 260F

        const val STANDING: String = "standing"
        const val MOVING: String = "moving"
    }

    var state: String = STANDING

    override var width: Float = WIDTH

    override var height: Float = HEIGHT

    var nextAction: String = STANDING

    override fun render(context: Context, canvas: Canvas) {
        val paint = ColorHelper.getColor(context, R.color.white)
        canvas.drawRect(x, y+HEIGHT, x+WIDTH, y, paint)
    }

    fun commitNextAction(intensity: Float): Boolean {
        lastIntensity = intensity
        state = nextAction
        if (nextAction === STANDING) {
            return false
        }

        if (nextAction === MOVING) {
            x += speed * intensity
        }
        return true
    }

    fun revertPreviousAction(intensity: Float) {
        x -= speed * intensity
        state = STANDING
    }
}