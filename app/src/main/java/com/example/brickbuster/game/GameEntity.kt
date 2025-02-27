package com.example.brickbuster.game

import android.content.Context
import android.graphics.Canvas

interface GameEntity {
    var x: Float

    var y: Float

    fun render(context: Context, canvas: Canvas) {}
}