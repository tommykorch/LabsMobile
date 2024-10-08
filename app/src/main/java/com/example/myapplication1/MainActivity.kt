package com.example.myapplication1
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
        val resultTextView = findViewById<TextView>(R.id.resultTextViewTask1)
        val inputField1 = findViewById<EditText>(R.id.inputField1)
        val resultTextView1= findViewById<TextView>(R.id.resultTextViewTask2)
        val inputField2 = findViewById<EditText>(R.id.inputField2)
        val resultTextView2= findViewById<TextView>(R.id.resultTextViewTask3)

        //val inputChar = inputField.text.toString().trim()
        inputField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // проверяем символ
                val inputChar = s.toString().trim()

                if (inputChar.length != 1 || !inputChar[0].isLowerCase() || !inputChar[0].isLetter()) {
                    resultTextView.text = "Введенный символ не является строчной латинской буквой."
                } else {
                    // Проверка на гласные
                    when (inputChar[0]) {
                        'a', 'e', 'i', 'o', 'u' -> resultTextView.text = "Это гласные буквы."
                        else -> resultTextView.text = "Возможно, это согласные буквы."
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        // Обработка ввода натурального числа n
        inputField1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val nStr = s.toString().trim()
                if (nStr.isNotEmpty()) {
                    val n = nStr.toIntOrNull()
                    if (n != null && n > 0) {
                        val sum = calculateSum(n)
                        resultTextView1.text = "Сумма S = $sum"
                    } else {
                        resultTextView1.text = "Введите натуральное число больше 0."
                    }
                } else {
                    resultTextView1.text = ""
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        inputField2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val inputString = s.toString()
                val (modifiedString, lowercaseCount) = processString(inputString)

                // Обновляем результат
                resultTextView2.text = "Обработанная строка:\n$modifiedString\nКоличество маленьких латинских букв: $lowercaseCount"
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun processString(input: String): Pair<String, Int> {
        // цифры на  пробелы
        val modifiedString = input.replace(Regex("\\d"), " ")

        // считаем латинские стрчоные буквы
        val lowercaseCount = input.count { it in 'a'..'z' }

        return Pair(modifiedString, lowercaseCount)
    }
    private fun calculateSum(n: Int): Double {
        var sum = 0.0
        var factorial = 1.0 // Начальное значение для 0!

        for (i in 1..n) {
            factorial *= i // Вычисляем i!
            sum += 1 / factorial // Добавляем 1/i!
        }

        return sum
    }
}