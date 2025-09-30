package com.example.game8386

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GuideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)

        val tvGuideContent = findViewById<TextView>(R.id.tvGuideContent)
        val btnBackToMain = findViewById<Button>(R.id.btnBackToMain)

        tvGuideContent.text = "Hướng dẫn chơi 2048:\n\n- Vuốt các ô để di chuyển các số.\n- Khi hai ô cùng số chạm nhau, chúng sẽ cộng lại.\n- Mục tiêu là đạt được ô 2048.\n- Sử dụng nút Hoàn lại để quay lại bước trước.\n- Chúc bạn chơi vui!"

        btnBackToMain.setOnClickListener {
            val intent = android.content.Intent(this, MainActivity::class.java)
            intent.flags = android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP or android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }
}
