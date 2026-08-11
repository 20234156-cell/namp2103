package com.example.game8386

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlayGame = findViewById<Button>(R.id.btnPlayGame)
        val btnLeaderboard = findViewById<Button>(R.id.btnLeaderboard)
        val btnSettings = findViewById<Button>(R.id.btnSettings)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val btnGuide = findViewById<Button>(R.id.btnGuide)

        btnPlayGame.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }
        btnLeaderboard.setOnClickListener {
            startActivity(Intent(this, LeaderboardActivity::class.java))
        }
        btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        btnLogout.setOnClickListener {
            // Return to login screen
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        btnGuide.setOnClickListener {
            startActivity(Intent(this, GuideActivity::class.java))
        }
    }
}
