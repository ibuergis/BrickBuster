package com.example.brickbuster.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.brickbuster.R

class LoseScreenView(): AppCompatActivity() {
    private var gameView: GameView? = null

    private fun loadLoseScreen() {
        val winscreen = layoutInflater.inflate(R.layout.lose_screen, null)
        setContentView(winscreen)
        val continueButton = findViewById<Button>(R.id.retryButton)
        continueButton.setOnClickListener {
            setContentView(gameView)
        }
        val winReturnButton = findViewById<Button>(R.id.loseReturnButton)
        winReturnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        gameView = GameView(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                )
        loadLoseScreen()
    }
}