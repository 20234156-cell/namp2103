package com.example.game8386

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class LeaderboardDbHelper(context: Context) : SQLiteOpenHelper(context, "leaderboard.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE leaderboard (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT,
                playerName TEXT,
                score INTEGER,
                elapsedTime INTEGER,
                mapSize INTEGER,
                colorMode INTEGER,
                difficulty TEXT,
                grid TEXT
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS leaderboard")
        onCreate(db)
    }

    fun saveScore(
        username: String,
        playerName: String,
        score: Int,
        elapsedTime: Int,
        mapSize: Int,
        colorMode: Int,
        difficulty: String,
        grid: String
    ) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("username", username)
            put("playerName", playerName)
            put("score", score)
            put("elapsedTime", elapsedTime)
            put("mapSize", mapSize)
            put("colorMode", colorMode)
            put("difficulty", difficulty)
            put("grid", grid)
        }
        db.insert("leaderboard", null, values)
        db.close()
    }

    fun getAllScores(): List<Map<String, Any>> {
        val db = readableDatabase
        val cursor = db.query("leaderboard", null, null, null, null, null, "score DESC")
        val scores = mutableListOf<Map<String, Any>>()
        while (cursor.moveToNext()) {
            val score = mapOf(
                "username" to cursor.getString(cursor.getColumnIndexOrThrow("username")),
                "playerName" to cursor.getString(cursor.getColumnIndexOrThrow("playerName")),
                "score" to cursor.getInt(cursor.getColumnIndexOrThrow("score")),
                "elapsedTime" to cursor.getInt(cursor.getColumnIndexOrThrow("elapsedTime")),
                "mapSize" to cursor.getInt(cursor.getColumnIndexOrThrow("mapSize")),
                "colorMode" to cursor.getInt(cursor.getColumnIndexOrThrow("colorMode")),
                "difficulty" to cursor.getString(cursor.getColumnIndexOrThrow("difficulty")),
                "grid" to cursor.getString(cursor.getColumnIndexOrThrow("grid"))
            )
            scores.add(score)
        }
        cursor.close()
        db.close()
        return scores
    }
}
