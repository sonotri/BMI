package com.example.bmi

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val bmiStatusTextView: TextView = findViewById(R.id.bmiStatusTextView)
        val bmiImageView: ImageView = findViewById(R.id.bmiImageView)
        val bmiResultTextView: TextView = findViewById(R.id.bmiResultTextView)

        val name = intent.getStringExtra("name") ?: "이름없음"
        val height = intent.getIntExtra("height", 0)
        val weight = intent.getIntExtra("weight", 0)

        val bmi = weight / Math.pow(height / 100.0, 2.0)
        val bmiString = "%.15f".format(bmi)

        // 상태 및 이미지 설정
        when {
            bmi < 18.5 -> {
                bmiStatusTextView.text = "저체중"
                bmiImageView.setImageResource(R.drawable.ic_sad)
            }
            bmi < 24.9 -> {
                bmiStatusTextView.text = "정상"
                bmiImageView.setImageResource(R.drawable.ic_happy)
            }
            else -> {
                bmiStatusTextView.text = "과체중"
                bmiImageView.setImageResource(R.drawable.ic_warning)
            }
        }

        bmiResultTextView.text = "$name : $bmiString"
    }
}
