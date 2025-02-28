package com.example.brickbuster.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.brickbuster.R

class MainActivity : AppCompatActivity() {

    var gameView: GameView? = null

    fun loadHomeScreen() {
        setContentView(R.layout.home_screen)
        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            gameView = GameView(this)
            setContentView(gameView)
        }
        val leaderboardButton = findViewById<Button>(R.id.leaderboardButton)
        leaderboardButton.setOnClickListener {
            val intent = Intent(this, LeaderboardView::class.java)
            this.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        loadHomeScreen()
    }
}