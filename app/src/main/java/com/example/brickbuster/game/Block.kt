package com.example.brickbuster.game

import android.content.Context
import android.graphics.Canvas
import com.example.brickbuster.R
import com.example.brickbuster.helper.ColorHelper

class Block(override var x: Float, override var y: Float, override var width: Float) : GameEntity {
    companion object {
        const val HEIGHT: Float = 40F
        const val BLOCK_COUNT = 10

        private fun generateXRow(yParameter: Float, xParameter: Float): MutableList<Block> {
            val width: Float = (Arena.ARENA_HALF_WIDTH * 2 - (5F * BLOCK_COUNT)) / BLOCK_COUNT
            val row: MutableList<Block> = mutableListOf()
            var x = xParameter
            var xList = 1
            while (xList <= BLOCK_COUNT) {
                row.add(Block(x, yParameter, width))
                x += width + 5F
                xList++
            }
            return row
        }

        fun generateNewGrid(xParameter: Float, yParameter: Float): MutableList<Block> {
            val grid: MutableList<Block> = mutableListOf()
            var y = yParameter
            var yList = 1
            while (yList <= 3) {
                grid += generateXRow(y, xParameter)
                y += HEIGHT + 5F
                yList++
            }
            return grid
        }
    }

    override var height: Float = HEIGHT

    override fun render(context: Context, canvas: Canvas) {
        val paint = ColorHelper.getColor(context, R.color.white)
        canvas.drawRect(x, y+ HEIGHT, x+ width, y, paint)
    }
}