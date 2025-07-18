package com.example.bmi

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var resultButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.nameEditText)
        heightEditText = findViewById(R.id.heightEditText)
        weightEditText = findViewById(R.id.weightEditText)
        resultButton = findViewById(R.id.resultButton)

        loadData()

        resultButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val height = heightEditText.text.toString().toIntOrNull()
            val weight = weightEditText.text.toString().toIntOrNull()

            if (name.isBlank() || height == null || weight == null) {
                Toast.makeText(this, "모든 정보를 입력해주세요!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            saveData(name, height, weight)

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)
            startActivity(intent)
        }
    }

    private fun saveData(name: String, height: Int, weight: Int) {
        val pref = getPreferences(MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("KEY_NAME", name)
        editor.putInt("KEY_HEIGHT", height)
        editor.putInt("KEY_WEIGHT", weight)
        editor.apply()
    }

    private fun loadData() {
        val pref = getPreferences(MODE_PRIVATE)
        val name = pref.getString("KEY_NAME", "")
        val height = pref.getInt("KEY_HEIGHT", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if (!name.isNullOrEmpty() && height != 0 && weight != 0) {
            nameEditText.setText(name)
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }
}
