package com.example.brickbuster.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.brickbuster.game.GameLoop
import com.example.brickbuster.R
import com.example.brickbuster.game.GameEntity
import com.example.brickbuster.game.Paddle
import com.example.brickbuster.helper.ColorHelper

class GameView(private val context: Context): SurfaceView(context), SurfaceHolder.Callback, SensorEventListener {

    var movementIntensity: Float = 0F

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

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
                    movementIntensity = 1F
                }
                else {
                    movementIntensity = -1F
                }
                gameLoop.queuePaddleAction(Paddle.MOVING)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                movementIntensity = 0F
                gameLoop.queuePaddleAction(Paddle.STANDING)
            }
        }
        return true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        gameLoop.startLoop()

        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {

            if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
                val rotationSpeed = event.values[2] * -1
                if (-0.1 < rotationSpeed && rotationSpeed < 0.1) {
                    return
                }
                movementIntensity += rotationSpeed / 8

                if (movementIntensity < 0.2 && movementIntensity > -0.2) {
                    gameLoop.queuePaddleAction(Paddle.STANDING)
                }
                else {
                    gameLoop.queuePaddleAction(Paddle.MOVING)
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
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