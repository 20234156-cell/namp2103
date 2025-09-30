package com.example.game8386

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LeaderboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        val tableLeaderboard = findViewById<TableLayout>(R.id.tableLeaderboard)
        val btnBack = findViewById<Button>(R.id.btnBackToMenuLeaderboard)

        // Xóa các dòng cũ (chỉ giữ header)
        while (tableLeaderboard.childCount > 1) {
            tableLeaderboard.removeViewAt(1)
        }
        // Sử dụng SQLite để lấy dữ liệu bảng xếp hạng
        val dbHelper = LeaderboardDbHelper(this)
        val scores = dbHelper.getAllScores()
        scores.forEachIndexed { index: Int, it: Map<String, Any> ->
            val row = TableRow(this)
            val min = (it["elapsedTime"] as Int) / 60
            val sec = (it["elapsedTime"] as Int) % 60
            val stt = TextView(this)
            stt.text = (index + 1).toString()
            stt.setPadding(8, 8, 8, 8)
            stt.gravity = android.view.Gravity.CENTER
            val name = TextView(this)
            name.text = it["playerName"].toString()
            name.setPadding(8, 8, 8, 8)
            name.gravity = android.view.Gravity.CENTER
            val score = TextView(this)
            score.text = it["score"].toString()
            score.setPadding(8, 8, 8, 8)
            score.gravity = android.view.Gravity.CENTER
            val time = TextView(this)
            time.text = "%02d:%02d".format(min, sec)
            time.setPadding(8, 8, 8, 8)
            time.gravity = android.view.Gravity.CENTER
            val map = TextView(this)
            map.text = "${it["mapSize"].toString()}x${it["mapSize"].toString()}"
            map.setPadding(8, 8, 8, 8)
            map.gravity = android.view.Gravity.CENTER
            val color = TextView(this)
            color.text = "Màu ${it["colorMode"].toString()}"
            color.setPadding(8, 8, 8, 8)
            color.gravity = android.view.Gravity.CENTER
            val diff = TextView(this)
            diff.text = if (it["difficulty"].toString() == "easy") "Dễ" else "Khó"
            diff.setPadding(8, 8, 8, 8)
            diff.gravity = android.view.Gravity.CENTER
            val rank = TextView(this)
            rank.text = (index + 1).toString()
            rank.setPadding(8, 8, 8, 8)
            rank.gravity = android.view.Gravity.CENTER
            row.addView(stt)
            row.addView(name)
            row.addView(score)
            row.addView(time)
            row.addView(map)
            row.addView(color)
            row.addView(diff)
            row.addView(rank)
            tableLeaderboard.addView(row)
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
