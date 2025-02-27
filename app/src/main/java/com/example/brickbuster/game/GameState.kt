package com.example.brickbuster.game

class GameState {
    companion object {
        const val STATE_HOME_SCREEN = "home_screen"
        const val STATE_LEVEL_MENU = "level_menu"
        const val STATE_GAME_START = "game_start"
        const val STATE_PLAYING = "playing"
        const val STATE_WIN = "win"
        const val STATE_LOSE = "lose"
    }

    var currentState: String = STATE_GAME_START

    var balls: MutableList<Ball> = mutableListOf()

    var blocks: MutableList<Block> = mutableListOf()

    var paddle: MutableList<Paddle> = mutableListOf()

    private fun onHomeScreen() {

    }

    private fun onLevelMenu() {

    }

    private fun onGameStart() {
        var thing = 0
        while (thing < 10) {
            balls.add(Ball(500F, 500F))
            thing++
        }
        paddle.add(Paddle(200F, 1000F))
        blocks = Block.generateNewGrid()
        currentState = STATE_PLAYING
    }

    private fun onPlaying() {
        for(ball in balls) {
            ball.move()
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

        return balls + blocks + paddle
    }
}