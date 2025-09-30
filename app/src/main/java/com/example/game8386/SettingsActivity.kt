package com.example.game8386

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val cbSound = findViewById<CheckBox>(R.id.cbSound)
        val cbVibration = findViewById<CheckBox>(R.id.cbVibration)
        val btnBack = findViewById<Button>(R.id.btnBackToMenuSettings)
        val prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)

        cbSound.isChecked = prefs.getBoolean("sound", true)
        cbVibration.isChecked = prefs.getBoolean("vibration", true)

        cbSound.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("sound", isChecked).apply()
        }
        cbVibration.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("vibration", isChecked).apply()
        }

        val rgMapSize = findViewById<RadioGroup>(R.id.rgMapSize)
        val rgColorMode = findViewById<RadioGroup>(R.id.rgColorMode)
        val rgDifficulty = findViewById<RadioGroup>(R.id.rgDifficulty)

        // Hiển thị lựa chọn đã lưu
        when (prefs.getInt("mapSize", 4)) {
            3 -> rgMapSize.check(R.id.rbMap3)
            4 -> rgMapSize.check(R.id.rbMap4)
            5 -> rgMapSize.check(R.id.rbMap5)
        }
        when (prefs.getInt("colorMode", 1)) {
            1 -> rgColorMode.check(R.id.rbColor1)
            2 -> rgColorMode.check(R.id.rbColor2)
            3 -> rgColorMode.check(R.id.rbColor3)
        }
        when (prefs.getString("difficulty", "easy")) {
            "easy" -> rgDifficulty.check(R.id.rbEasy)
            "hard" -> rgDifficulty.check(R.id.rbHard)
        }

        rgMapSize.setOnCheckedChangeListener { _, checkedId ->
            val value = when (checkedId) {
                R.id.rbMap3 -> 3
                R.id.rbMap4 -> 4
                R.id.rbMap5 -> 5
                else -> 4
            }
            prefs.edit().putInt("mapSize", value).apply()
        }
        rgColorMode.setOnCheckedChangeListener { _, checkedId ->
            val value = when (checkedId) {
                R.id.rbColor1 -> 1
                R.id.rbColor2 -> 2
                R.id.rbColor3 -> 3
                else -> 1
            }
            prefs.edit().putInt("colorMode", value).apply()
        }
        rgDifficulty.setOnCheckedChangeListener { _, checkedId ->
            val value = when (checkedId) {
                R.id.rbEasy -> "easy"
                R.id.rbHard -> "hard"
                else -> "easy"
            }
            prefs.edit().putString("difficulty", value).apply()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
