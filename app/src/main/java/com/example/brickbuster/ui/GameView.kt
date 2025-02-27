package com.example.brickbuster.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat
import com.example.brickbuster.game.GameLoop
import com.example.brickbuster.R

class GameView(context: Context): SurfaceView(context), SurfaceHolder.Callback {

    private val gameLoop: GameLoop

    private val context: Context

    init {
        holder.addCallback(this)
        gameLoop = GameLoop(holder, this)

        isFocusable = true
        this.context = context
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        gameLoop.startLoop()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        drawUPS(canvas)
        drawFPS(canvas)
    }

    fun update() {

    }

    private fun drawUPS(canvas: Canvas) {
        val averageUPS: String = gameLoop.getAverageUPS().toString()
        val paint = Paint()
        val color = ContextCompat.getColor(context, R.color.neonGreen)
        paint.setColor(color)
        paint.textSize = 100F
        canvas.drawText("UPS: $averageUPS", 100F, 110F, paint)
    }

    private fun drawFPS(canvas: Canvas) {
        val averageFPS: String = gameLoop.getAverageFPS().toString()
        val paint = Paint()
        val color = ContextCompat.getColor(context, R.color.neonGreen)
        paint.setColor(color)
        paint.textSize = 100F
        canvas.drawText("FPS: $averageFPS", 100F, 210F, paint)
    }
}