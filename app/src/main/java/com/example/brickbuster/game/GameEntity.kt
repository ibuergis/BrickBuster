package com.example.brickbuster.game

import android.graphics.Canvas

interface GameEntity {

    var x: Float

    var y: Float

    fun render(canvas: Canvas) {}
}