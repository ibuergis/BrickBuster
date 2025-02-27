package com.example.brickbuster.game

import com.example.brickbuster.game.Arena.Companion.ARENA_HALF_HEIGHT
import com.example.brickbuster.game.Arena.Companion.ARENA_HALF_WIDTH

class GameState(screenWidth: Float, screenHeight: Float) {
    companion object {
        const val STATE_HOME_SCREEN = "home_screen"
        const val STATE_LEVEL_MENU = "level_menu"
        const val STATE_GAME_START = "game_start"
        const val STATE_PLAYING = "playing"
        const val STATE_WIN = "win"
        const val STATE_LOSE = "lose"
    }

    val left = screenWidth/2 - ARENA_HALF_WIDTH
    val top = screenHeight/2 - ARENA_HALF_HEIGHT
    val right = screenWidth/2 + ARENA_HALF_WIDTH
    val bottom = screenHeight/2 + ARENA_HALF_HEIGHT

    var currentState: String = STATE_GAME_START

    var balls: MutableList<Ball> = mutableListOf()

    var blocks: MutableList<Block> = mutableListOf()

    var paddle: MutableList<Paddle> = mutableListOf()

    var arena: MutableList<Arena> = mutableListOf()

    fun clear() {
        balls = mutableListOf()
        blocks = mutableListOf()
        paddle = mutableListOf()
        arena = mutableListOf()
    }

    private fun onHomeScreen() {

    }

    private fun onLevelMenu() {

    }

    private fun onGameStart() {
        arena.add(Arena(left, top, right, bottom))
        balls.add(Ball(left + ARENA_HALF_WIDTH, top + ARENA_HALF_HEIGHT))
        paddle.add(Paddle(left + ARENA_HALF_WIDTH - Paddle.WIDTH / 2, bottom - 50F))
        blocks = Block.generateNewGrid(left + 1F, top + 1F)
        currentState = STATE_PLAYING
    }

    private fun onPlaying() {
        val arena = this.arena.first()
        val paddle = this.paddle.first()
        val moveMade = paddle.commitNextAction()
        if (moveMade && arena.entityIsOutOfBounds(paddle) !== null) {
            paddle.revertPreviousAction()
        }
        for(ball in balls) {
            ball.move()
            val result = arena.entityIsOutOfBounds(ball)
            if (result !== null) {
                ball.calculateRedirection(arena, result)
                if (ball.y + ball.height > arena.bottom) {
                    balls.remove(ball)
                    if (balls.isEmpty()) {
                        currentState = STATE_LOSE
                    }
                }
            }
            for (gameEntity in blocks + paddle) {
                val result = ball.hasCollided(gameEntity)
                if (result !== null) {
                    if (gameEntity is Block) {
                        blocks.remove(gameEntity)
                        if (blocks.isEmpty()) {
                            currentState = STATE_WIN
                        }
                    }
                    ball.calculateRedirection(gameEntity, result)
                }
            }
        }
    }

    private fun onWin() {

    }

    private fun onLose() {

    }

    fun update(): List<GameEntity> {
        when (currentState) {
            STATE_HOME_SCREEN -> onHomeScreen()
            STATE_LEVEL_MENU -> onLevelMenu()
            STATE_GAME_START -> onGameStart()
            STATE_PLAYING -> onPlaying()
            STATE_WIN -> onWin()
            STATE_LOSE -> onLose()
        }

        return arena + paddle + balls + blocks
    }

    fun queuePaddleAction(movement: String) {
        paddle.first().nextAction = movement
    }
}