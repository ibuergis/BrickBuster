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

    var balls: List<Ball> = listOf()

    var blocks: List<Block> = listOf()

    var paddle: Paddle? = null

    private fun onHomeScreen() {

    }

    private fun onLevelMenu() {

    }

    private fun onGameStart() {

    }

    private fun onPlaying() {

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

        return listOf(Paddle(200F, 1000F))
    }
}