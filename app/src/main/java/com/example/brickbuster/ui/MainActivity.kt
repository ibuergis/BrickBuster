package com.example.brickbuster.ui

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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                )

        loadHomeScreen()
    }
}