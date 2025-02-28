package com.example.brickbuster.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.brickbuster.game.GameLoop
import com.example.brickbuster.R
import com.example.brickbuster.game.GameEntity
import com.example.brickbuster.game.Paddle
import com.example.brickbuster.helper.ColorHelper

class GameView(private val context: Context): SurfaceView(context), SurfaceHolder.Callback {

    val gameLoop: GameLoop

    init {
        holder.addCallback(this)
        gameLoop = GameLoop(holder, this)

        isFocusable = true
        isFocusableInTouchMode = true
    }



    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)

        if (event === null) {
            return false
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (event.x > gameLoop.canvasWidth / 2) {
                    gameLoop.queuePaddleAction(Paddle.MOVING_RIGHT)
                }
                else {
                    gameLoop.queuePaddleAction(Paddle.MOVING_LEFT)
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                gameLoop.queuePaddleAction(Paddle.STANDING)
            }
        }
        return true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        gameLoop.startLoop()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
    }

    fun draw(canvas: Canvas, gameEntities: List<GameEntity>) {
        super.draw(canvas)
        for (gameEntity in gameEntities) {
            gameEntity.render(context, canvas)
        }
        drawUPS(canvas)
        drawFPS(canvas)
    }

    private fun drawUPS(canvas: Canvas) {
        val averageUPS: String = gameLoop.getAverageUPS().toString()
        val paint = ColorHelper.getColor(context, R.color.neonGreen)
        paint.textSize = 100F
        canvas.drawText("UPS: $averageUPS", 100F, 110F, paint)
    }

    private fun drawFPS(canvas: Canvas) {
        val averageFPS: String = gameLoop.getAverageFPS().toString()
        val paint = ColorHelper.getColor(context, R.color.neonGreen)
        paint.textSize = 100F
        canvas.drawText("FPS: $averageFPS", 100F, 210F, paint)
    }

    fun loadWinScreen() {
        val intent = Intent(context, WinScreenView::class.java)
        context.startActivity(intent)
    }

    fun loadLoseScreen() {
        val intent = Intent(context, LoseScreenView::class.java)
        context.startActivity(intent)
    }

    fun loadHomeScreen() {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }
}