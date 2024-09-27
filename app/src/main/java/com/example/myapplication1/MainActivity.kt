package com.example.myapplication1
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val inputField = findViewById<EditText>(R.id.inputField)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val inputChar = inputField.text.toString().trim()

        if (inputChar.length != 1 || !inputChar[0].isLowerCase() || !inputChar[0].isLetter()) {
            resultTextView.text = "Введенный символ не является строчной латинской буквой."
            return
        }

        // Проверка на гласные
        when (inputChar[0]) {
            'a', 'e', 'i', 'o', 'u' -> resultTextView.text = "Это гласные буквы."
            else -> resultTextView.text = "Возможно, это согласные буквы."
        }
    }
    }
