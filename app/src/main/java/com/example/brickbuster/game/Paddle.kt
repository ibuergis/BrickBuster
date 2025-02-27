package com.example.brickbuster.game

import android.content.Context
import android.graphics.Canvas
import com.example.brickbuster.R
import com.example.brickbuster.helper.ColorHelper

class Paddle(
    override var x: Float,
    override var y: Float
): GameEntity {

    private val speed: Float = 20F

    companion object {
        const val HEIGHT: Float = 20F
        const val WIDTH: Float = 260F

        const val MOVING_LEFT: String = "left"
        const val MOVING_RIGHT: String = "right"
        const val STANDING: String = "standing"
    }

    var state: String = STANDING

    override var width: Float = WIDTH

    override var height: Float = HEIGHT

    var nextAction: String = STANDING

    override fun render(context: Context, canvas: Canvas) {
        val paint = ColorHelper.getColor(context, R.color.white)
        canvas.drawRect(x, y+HEIGHT, x+WIDTH, y, paint)
    }

    fun commitNextAction(): Boolean {
        state = nextAction
        if (nextAction === STANDING) {
            return false
        }

        if (nextAction === MOVING_LEFT) {
            moveLeft()
        }
        else if (nextAction === MOVING_RIGHT) {
            moveRight()
        }
        return true
    }

    fun revertPreviousAction() {
        if (nextAction === MOVING_LEFT) {
            moveRight()
        }
        else if (nextAction === MOVING_RIGHT) {
            moveLeft()
        }
        state = STANDING
    }

    fun moveLeft() {
        x -= speed
    }

    fun moveRight() {
        x += speed
    }
}