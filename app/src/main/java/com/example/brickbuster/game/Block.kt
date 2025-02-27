package com.example.brickbuster.game

import android.content.Context
import android.graphics.Canvas
import com.example.brickbuster.R
import com.example.brickbuster.helper.ColorHelper

class Block(override var x: Float, override var y: Float) : GameEntity {
    companion object {
        const val HEIGHT: Float = 40F
        const val WIDTH: Float = 180F
        const val BLOCK_COUNT = 10

        private fun generateXRow(y: Float): MutableList<Block> {
            val row: MutableList<Block> = mutableListOf()
            var x = 0F
            var xList = 1
            while (xList <= BLOCK_COUNT) {
                row.add(Block(x, y))
                x += WIDTH + 5F
                xList++
            }
            return row
        }

        fun generateNewGrid(): MutableList<Block> {
            val grid: MutableList<Block> = mutableListOf()
            var y = HEIGHT
            var yList = 1
            while (yList <= 3) {
                grid += generateXRow(y)
                y += HEIGHT + 5F
                yList++
            }
            return grid
        }
    }

    override fun render(context: Context, canvas: Canvas) {
        val paint = ColorHelper.getColor(context, R.color.white)
        canvas.drawRect(x, y+ HEIGHT, x+ WIDTH, y, paint)
    }
}