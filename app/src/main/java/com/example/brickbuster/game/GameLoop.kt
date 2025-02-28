package com.example.brickbuster.game

import android.view.SurfaceHolder
import com.example.brickbuster.ui.GameView

// With help from https://www.youtube.com/watch?v=5iFOYfpKOfs
class GameLoop(
    private val surfaceHolder: SurfaceHolder,
    private val gameView: GameView

    ): Thread() {

    private val MAX_UPS: Double = 60.0
    private val TARGET_UPS: Double = 1000/MAX_UPS

    private val MAX_FPS: Double = 30.0
    private val TARGET_FPS: Double = 1000/MAX_FPS

    private var isRunning: Boolean = true
    private var averageUPS: Double = 0.0
    private var averageFPS: Double = 0.0

    private var gameStateInitialized = false

    var canvasWidth: Int = 0

    var canvasHeight: Int = 0

    private var gameState: GameState = GameState(0F, 0F, gameView)

    fun getAverageUPS(): Double {
        return averageUPS
    }

    fun getAverageFPS(): Double {
        return averageFPS
    }

    fun startLoop() {
        if(!gameStateInitialized) {
            val canvas = surfaceHolder.lockCanvas()
            gameState = GameState(canvas.width.toFloat(), canvas.height.toFloat(), gameView)
            canvasWidth = canvas.width
            canvasHeight = canvas.height
            surfaceHolder.unlockCanvasAndPost(canvas)
            gameStateInitialized = true
        }
        isRunning = true
        start()
    }

    fun endLoop() {
        isRunning = false
    }

    override fun run() {
        var updateCount: Int = 0
        var frameCount: Int = 0

        var startTime: Long
        var elapsedTime: Long
        var sleepTime: Long

        super.run()
        startTime = System.currentTimeMillis()
        while(isRunning) {
            val canvas = surfaceHolder.lockCanvas()
            val gameEntities: List<GameEntity> = gameState.update()
            gameView.draw(canvas, gameEntities)
            surfaceHolder.unlockCanvasAndPost(canvas)

            updateCount++
            frameCount++

            elapsedTime = System.currentTimeMillis() - startTime
            sleepTime = (updateCount*TARGET_UPS - elapsedTime).toLong()
            if(sleepTime > 0) {
                sleep(sleepTime)
            }

            if(elapsedTime >= 1000) {
                averageUPS = updateCount / (0.001 * elapsedTime)
                averageFPS = frameCount / (0.001 * elapsedTime)
                updateCount = 0
                frameCount = 0
                startTime = System.currentTimeMillis()
            }
        }

    }

    fun queuePaddleAction(movement: String) {
        gameState.queuePaddleAction(movement)
    }

}
