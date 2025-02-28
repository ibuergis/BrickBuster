package com.example.brickbuster.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.brickbuster.R
import com.example.brickbuster.game.GameState

class LeaderboardView : AppCompatActivity() {
    @SuppressLint("DefaultLocale")
    private fun loadLeaderboardView() {
        val leaderboardView = layoutInflater.inflate(R.layout.leaderboard_screen, null)
        setContentView(leaderboardView)

        var placement = 1
        val leaderboard = GameState.getLeaderboard(this)

        while (placement <= 10) {
            val id = resources.getIdentifier("score$placement", "id", "com.example.brickbuster")
            val highScoreTextView = findViewById<TextView>(id)
            highScoreTextView.text = String.format("%07d", leaderboard[placement-1])
            placement++
        }

        val lbReturnButton = findViewById<Button>(R.id.lbReturnButton)
        lbReturnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        loadLeaderboardView()
    }
}