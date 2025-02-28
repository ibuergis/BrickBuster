package com.example.brickbuster.game

import android.content.Context
import android.content.SharedPreferences
import com.example.brickbuster.game.Arena.Companion.ARENA_HALF_HEIGHT
import com.example.brickbuster.game.Arena.Companion.ARENA_HALF_WIDTH
import com.example.brickbuster.ui.GameView

class GameState(screenWidth: Float, screenHeight: Float, private val gameView: GameView) {
    companion object {
        const val STATE_HOME_SCREEN = "home_screen"
        const val STATE_LEVEL_MENU = "level_menu"
        const val STATE_GAME_START = "game_start"
        const val STATE_PLAYING = "playing"
        const val STATE_WIN = "win"
        const val STATE_LOSE = "lose"

        fun getLeaderboard(context: Context): MutableList<Int> {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences("Leaderboard", Context.MODE_PRIVATE)
            val mutableList: MutableList<Int> = mutableListOf()
            var placement = 1
            while (placement <= 10) {
                mutableList.add(sharedPreferences.getInt(placement.toString(), 0))
                placement++
            }
            return mutableList
        }

        fun setLeaderboard(context: Context, leaderboard: List<Int>) {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences("Leaderboard", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            var placement = 1
            for (score in leaderboard) {
                editor.putInt(placement.toString(), score)
                placement++
            }
            editor.apply()
        }
    }

    private val left = screenWidth/2 - ARENA_HALF_WIDTH
    private val top = screenHeight/2 - ARENA_HALF_HEIGHT
    private val right = screenWidth/2 + ARENA_HALF_WIDTH
    private val bottom = screenHeight/2 + ARENA_HALF_HEIGHT

    private var currentState: String = STATE_GAME_START

    private var scores: MutableList<Score> = mutableListOf()

    private var balls: MutableList<Ball> = mutableListOf()

    private var blocks: MutableList<Block> = mutableListOf()

    private var paddle: MutableList<Paddle> = mutableListOf()

    private var arena: MutableList<Arena> = mutableListOf()

    private fun onHomeScreen() {
        gameView.loadHomeScreen()
    }

    private fun onLevelMenu() {

    }

    private fun onGameStart() {
        gameView.movementIntensity = 0F
        scores.add(Score(left, top-20F))
        arena.add(Arena(left, top, right, bottom))
        balls.add(Ball(left + ARENA_HALF_WIDTH, top + ARENA_HALF_HEIGHT))
        paddle.add(Paddle(left + ARENA_HALF_WIDTH - Paddle.WIDTH / 2, bottom - 50F))
        blocks = Block.generateNewGrid(left + 1F, top + 1F)
        currentState = STATE_PLAYING
    }

    private fun onPlaying() {
        val arena = this.arena.first()
        val paddle = this.paddle.first()
        val score = this.scores.first()
        val moveMade = paddle.commitNextAction(gameView.movementIntensity)
        if (moveMade && arena.entityIsOutOfBounds(paddle) !== null) {
            paddle.revertPreviousAction(gameView.movementIntensity)
        }
        // lazy and hacky fix for java.util.ConcurrentModificationException
        for(ball in balls + listOf()) {
            ball.move()
            val result = arena.entityIsOutOfBounds(ball)
            if (result !== null) {
                ball.calculateRedirection(arena, result)
                if (ball.y + ball.height > arena.bottom) {
                    balls.remove(ball)
                    if (balls.isEmpty()) {
                        currentState = STATE_LOSE
                        gameView.loadWinScreen()
                    }
                }
            }
            for (gameEntity in blocks + paddle) {
                val result = ball.hasCollided(gameEntity)
                if (result !== null) {
                    if (gameEntity is Block) {
                        blocks.remove(gameEntity)
                        score.score += 100
                        if (blocks.isEmpty()) {
                            currentState = STATE_WIN
                        }
                        if (!blockFamilyStillExists(gameEntity.y)) {
                            score.score += 2000
                        }
                    }
                    ball.calculateRedirection(gameEntity, result)
                }
            }
        }
    }

    private fun blockFamilyStillExists(y: Float): Boolean {
        for (block in blocks) {
            if (block.y == y) {
                return true
            }
        }
        return false
    }

    private fun onWin() {
        addScoreToLeaderboard()
        gameView.gameLoop.endLoop()
        gameView.loadWinScreen()
    }

    private fun onLose() {
        addScoreToLeaderboard()
        gameView.gameLoop.endLoop()
        gameView.loadLoseScreen()
    }

    private fun addScoreToLeaderboard() {
        val score = scores.first()
        val leaderboard = getLeaderboard(gameView.context)

        var placement = 0
        while (placement < 10) {
            val highscore = leaderboard[placement]
            if (score.score > highscore) {
                leaderboard.add(placement, score.score)
                leaderboard.remove(10)
                break
            }
            placement++
        }
        setLeaderboard(gameView.context, leaderboard)
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

        return arena + paddle + balls + blocks + scores
    }

    fun queuePaddleAction(movement: String) {
        paddle.first().nextAction = movement
    }
}