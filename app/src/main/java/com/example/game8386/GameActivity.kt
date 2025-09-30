package com.example.game8386

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.os.Handler
import android.os.Looper

class GameActivity : AppCompatActivity() {
    private lateinit var grid: Array<IntArray>
    private lateinit var gridLayout: GridLayout
    private lateinit var tvScore: TextView
    private lateinit var tvPlayerName: TextView
    private lateinit var tvTimer: TextView
    private var score = 0
    private var mapSize: Int = 4
    private var colorMode: Int = 1
    private var difficulty: String = "easy"
    private lateinit var tvMapInfo: TextView
    private var playerName: String = ""
    private var startTime: Long = 0L
    private var elapsedTime: Int = 0
    private val timerHandler = Handler(Looper.getMainLooper())
    private lateinit var timerRunnable: Runnable
    private var undoGrid: Array<IntArray>? = null
    private var undoScore: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_game)

            gridLayout = findViewById(R.id.gridGame)
            tvScore = findViewById(R.id.tvScore)
            tvPlayerName = findViewById(R.id.tvPlayerName)
            tvTimer = findViewById(R.id.tvTimer)
            val btnRestart = findViewById<Button>(R.id.btnRestart)
            val btnBackToMenu = findViewById<Button>(R.id.btnBackToMenu)
            val btnUndo = findViewById<Button>(R.id.btnUndo)

            val prefs = getSharedPreferences("users", MODE_PRIVATE)
            playerName = prefs.getString("lastUser", "Khách") ?: "Khách"
            tvPlayerName.text = getString(R.string.player_name, playerName)

            val settings = getSharedPreferences("settings", MODE_PRIVATE)
            mapSize = settings.getInt("mapSize", 4)
            colorMode = settings.getInt("colorMode", 1)
            difficulty = settings.getString("difficulty", "easy") ?: "easy"

            tvMapInfo = findViewById(R.id.tvMapInfo)
            tvMapInfo.text = getString(R.string.map_info, mapSize, mapSize, colorMode, if (difficulty == "easy") getString(R.string.easy) else getString(R.string.hard))
            tvMapInfo.textSize = 16f
            tvMapInfo.setTextColor(0xFF776E65.toInt())

            btnRestart.setOnClickListener { restartGame() }
            btnBackToMenu.setOnClickListener {
                stopTimer()
                saveScore()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            btnUndo.setOnClickListener { undoMove() }

            setupGrid()
            updateUI()
            startTimer()

            val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
                override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                    if (e1 == null) return false
                    saveUndoState()
                    val dx = e2.x - e1.x
                    val dy = e2.y - e1.y
                    if (Math.abs(dx) > Math.abs(dy)) {
                        if (dx > 0) moveRight() else moveLeft()
                    } else {
                        if (dy > 0) moveDown() else moveUp()
                    }
                    return true
                }
            })

            gridLayout.setOnTouchListener { _, event ->
                gestureDetector.onTouchEvent(event)
                true
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Lỗi khi khởi tạo GameActivity: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    private fun startTimer() {
        startTime = System.currentTimeMillis()
        timerRunnable = object : Runnable {
            override fun run() {
                elapsedTime = ((System.currentTimeMillis() - startTime) / 1000).toInt()
                tvTimer.text = getString(R.string.timer, elapsedTime / 60, elapsedTime % 60)
                timerHandler.postDelayed(this, 1000)
            }
        }
        timerHandler.post(timerRunnable)
    }

    private fun stopTimer() {
        timerHandler.removeCallbacks(timerRunnable)
    }

    // Đã có biến mapSize, thay thế tất cả 'size' bằng 'mapSize' trong các hàm liên quan
    private fun saveUndoState() {
        undoGrid = Array(mapSize) { grid[it].clone() }
        undoScore = score
    }

    private fun undoMove() {
        if (undoGrid != null && undoScore != null) {
            grid = Array(mapSize) { undoGrid!![it].clone() }
            score = undoScore!!
            updateUI()
            Toast.makeText(this, "Đã hoàn lại 1 bước!", Toast.LENGTH_SHORT).show()
            undoGrid = null
            undoScore = null
        } else {
            Toast.makeText(this, "Không có bước nào để hoàn lại!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupGrid() {
        grid = Array(mapSize) { IntArray(mapSize) { 0 } }
        score = 0
        elapsedTime = 0
        addRandomTile()
        addRandomTile()
    }

    private fun restartGame() {
        stopTimer()
        setupGrid()
        updateUI()
        startTimer()
    }

    private fun addRandomTile() {
        val empty = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until mapSize) {
            for (j in 0 until mapSize) {
                if (grid[i][j] == 0) empty.add(Pair(i, j))
            }
        }
        if (empty.isNotEmpty()) {
            val (i, j) = empty[Random.nextInt(empty.size)]
            val value = if (difficulty == "easy") {
                if (Random.nextInt(10) < 8) 2 else 4
            } else {
                if (Random.nextInt(10) < 4) 2 else 4
            }
            grid[i][j] = value
        }
    }

    private fun updateUI() {
        gridLayout.removeAllViews()
        gridLayout.rowCount = mapSize
        gridLayout.columnCount = mapSize
        for (i in 0 until mapSize) {
            for (j in 0 until mapSize) {
                val tv = TextView(this)
                tv.text = if (grid[i][j] == 0) "" else grid[i][j].toString()
                tv.textSize = 24f
                tv.setBackgroundColor(getTileColor(grid[i][j]))
                tv.setTextColor(if (grid[i][j] <= 4) 0xFF776E65.toInt() else 0xFFFFFFFF.toInt())
                tv.gravity = android.view.Gravity.CENTER
                val params = GridLayout.LayoutParams()
                params.width = 0
                params.height = 0
                params.rowSpec = GridLayout.spec(i, 1f)
                params.columnSpec = GridLayout.spec(j, 1f)
                params.setMargins(4, 4, 4, 4)
                tv.layoutParams = params
                gridLayout.addView(tv)
            }
        }
        tvScore.text = getString(R.string.score, score)
        tvTimer.text = getString(R.string.timer, elapsedTime / 60, elapsedTime % 60)
        tvMapInfo.text = getString(R.string.map_info, mapSize, mapSize, colorMode, if (difficulty == "easy") getString(R.string.easy) else getString(R.string.hard))
    }

    private fun getTileColor(value: Int): Int {
        return when (colorMode) {
            1 -> when (value) {
                0 -> 0xFFCCC0B3.toInt()
                2 -> 0xFFEEE4DA.toInt()
                4 -> 0xFFEDE0C8.toInt()
                8 -> 0xFFF2B179.toInt()
                16 -> 0xFFF59563.toInt()
                32 -> 0xFFF67C5F.toInt()
                64 -> 0xFFF65E3B.toInt()
                128 -> 0xFFEDCF72.toInt()
                256 -> 0xFFEDCC61.toInt()
                512 -> 0xFFEDC850.toInt()
                1024 -> 0xFFEDC53F.toInt()
                2048 -> 0xFFEDC22E.toInt()
                else -> 0xFF3C3A32.toInt()
            }
            2 -> when (value) {
                0 -> 0xFFB0E0E6.toInt()
                2 -> 0xFFAFEEEE.toInt()
                4 -> 0xFFADD8E6.toInt()
                8 -> 0xFF87CEEB.toInt()
                16 -> 0xFF87CEFA.toInt()
                32 -> 0xFF4682B4.toInt()
                64 -> 0xFF5F9EA0.toInt()
                128 -> 0xFF6495ED.toInt()
                256 -> 0xFF00BFFF.toInt()
                512 -> 0xFF1E90FF.toInt()
                1024 -> 0xFF4169E1.toInt()
                2048 -> 0xFF0000CD.toInt()
                else -> 0xFF191970.toInt()
            }
            3 -> when (value) {
                0 -> 0xFFFFF8DC.toInt()
                2 -> 0xFFFFE4B5.toInt()
                4 -> 0xFFFFD700.toInt()
                8 -> 0xFFFFA500.toInt()
                16 -> 0xFFFF8C00.toInt()
                32 -> 0xFFFF6347.toInt()
                64 -> 0xFFFF4500.toInt()
                128 -> 0xFFFF1493.toInt()
                256 -> 0xFFFF69B4.toInt()
                512 -> 0xFFFFB6C1.toInt()
                1024 -> 0xFFFFC0CB.toInt()
                2048 -> 0xFFFFE4E1.toInt()
                else -> 0xFF8B0000.toInt()
            }
            else -> 0xFFCCC0B3.toInt()
        }
    }

    private fun moveLeft() {
        var moved = false
        for (i in 0 until mapSize) {
            val row = grid[i]
            val merged = BooleanArray(mapSize)
            for (j in 1 until mapSize) {
                if (row[j] == 0) continue
                var k = j
                while (k > 0 && row[k - 1] == 0) {
                    row[k - 1] = row[k]
                    row[k] = 0
                    k--
                    moved = true
                }
                if (k > 0 && row[k - 1] == row[k] && !merged[k - 1]) {
                    row[k - 1] *= 2
                    score += row[k - 1]
                    row[k] = 0
                    merged[k - 1] = true
                    moved = true
                }
            }
        }
        if (moved) {
            addRandomTile()
            updateUI()
            checkGameOver()
        }
    }

    private fun moveRight() {
        var moved = false
        for (i in 0 until mapSize) {
            val row = grid[i]
            val merged = BooleanArray(mapSize)
            for (j in mapSize - 2 downTo 0) {
                if (row[j] == 0) continue
                var k = j
                while (k < mapSize - 1 && row[k + 1] == 0) {
                    row[k + 1] = row[k]
                    row[k] = 0
                    k++
                    moved = true
                }
                if (k < mapSize - 1 && row[k + 1] == row[k] && !merged[k + 1]) {
                    row[k + 1] *= 2
                    score += row[k + 1]
                    row[k] = 0
                    merged[k + 1] = true
                    moved = true
                }
            }
        }
        if (moved) {
            addRandomTile()
            updateUI()
            checkGameOver()
        }
    }

    private fun moveUp() {
        var moved = false
        for (j in 0 until mapSize) {
            val merged = BooleanArray(mapSize)
            for (i in 1 until mapSize) {
                if (grid[i][j] == 0) continue
                var k = i
                while (k > 0 && grid[k - 1][j] == 0) {
                    grid[k - 1][j] = grid[k][j]
                    grid[k][j] = 0
                    k--
                    moved = true
                }
                if (k > 0 && grid[k - 1][j] == grid[k][j] && !merged[k - 1]) {
                    grid[k - 1][j] *= 2
                    score += grid[k - 1][j]
                    grid[k][j] = 0
                    merged[k - 1] = true
                    moved = true
                }
            }
        }
        if (moved) {
            addRandomTile()
            updateUI()
            checkGameOver()
        }
    }

    private fun moveDown() {
        var moved = false
        for (j in 0 until mapSize) {
            val merged = BooleanArray(mapSize)
            for (i in mapSize - 2 downTo 0) {
                if (grid[i][j] == 0) continue
                var k = i
                while (k < mapSize - 1 && grid[k + 1][j] == 0) {
                    grid[k + 1][j] = grid[k][j]
                    grid[k][j] = 0
                    k++
                    moved = true
                }
                if (k < mapSize - 1 && grid[k + 1][j] == grid[k][j] && !merged[k + 1]) {
                    grid[k + 1][j] *= 2
                    score += grid[k + 1][j]
                    grid[k][j] = 0
                    merged[k + 1] = true
                    moved = true
                }
            }
        }
        if (moved) {
            addRandomTile()
            updateUI()
            checkGameOver()
        }
    }

    private fun checkGameOver() {
        for (i in 0 until mapSize) {
            for (j in 0 until mapSize) {
                if (grid[i][j] == 0) return
                if (i > 0 && grid[i][j] == grid[i - 1][j]) return
                if (i < mapSize - 1 && grid[i][j] == grid[i + 1][j]) return
                if (j > 0 && grid[i][j] == grid[i][j - 1]) return
                if (j < mapSize - 1 && grid[i][j] == grid[i][j + 1]) return
            }
        }
        stopTimer()
        saveScore()
        Toast.makeText(this, "Kết thúc game!", Toast.LENGTH_SHORT).show()
    }

    private fun saveScore() {
        // Lấy username từ SharedPreferences (đã lưu khi đăng nhập)
        val userPrefs = getSharedPreferences("users", MODE_PRIVATE)
        val username = userPrefs.getString("lastUser", "Khách") ?: "Khách"
        // Chuyển trạng thái lưới thành chuỗi để lưu
        val gridString = grid.joinToString(";") { it.joinToString(",") }
        val dbHelper = LeaderboardDbHelper(this)
        dbHelper.saveScore(
            username,
            playerName,
            score,
            elapsedTime,
            mapSize,
            colorMode,
            difficulty,
            gridString
        )
    }
}
