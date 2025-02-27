package com.example.brickbuster.game

import android.content.Context
import android.graphics.Canvas

interface GameEntity {
    var x: Float

    var y: Float

    var height: Float

    var width: Float

    fun render(context: Context, canvas: Canvas) {}

    fun hasCollided(gameEntity: GameEntity): String? {
        if (
            x < gameEntity.x + gameEntity.width && x + width > gameEntity.x &&
            y < gameEntity.y + gameEntity.height && y + height > gameEntity.y
            ) {

            var xDifference = x - gameEntity.x
            var yDifference = y - gameEntity.y

            if (xDifference < 0) {
                xDifference *= -1
            }

            if (yDifference < 0) {
                yDifference *= -1
            }

            if (yDifference > xDifference) {
                if (y > gameEntity.y) {
                    return "top"
                }
                else {
                    return "bottom"
                }
            }
            else {
                if (x > gameEntity.x) {
                    return "left"
                }
                else {
                    return "right"
                }
            }
        }
        return null
    }
}