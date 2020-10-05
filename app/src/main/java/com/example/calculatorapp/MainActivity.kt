package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Numbers
        tvOne.setOnClickListener { appendOnExpression("1", true) }
        tvTwo.setOnClickListener { appendOnExpression("2", true) }
        tvThree.setOnClickListener { appendOnExpression("3", true) }
        tvFour.setOnClickListener { appendOnExpression("4", true) }
        tvFive.setOnClickListener { appendOnExpression("5", true) }
        tvSix.setOnClickListener { appendOnExpression("6", true) }
        tvSeven.setOnClickListener { appendOnExpression("7", true) }
        tvEight.setOnClickListener { appendOnExpression("8", true) }
        tvNine.setOnClickListener { appendOnExpression("9", true) }
        tvZero.setOnClickListener { appendOnExpression("0", true) }
        tvDot.setOnClickListener { appendOnExpression(".", true) }

        // Symbold
        tvAdd.setOnClickListener { appendOnExpression("+", false) }
        tvMinus.setOnClickListener { appendOnExpression("-", false) }
        tvDivision.setOnClickListener { appendOnExpression("/", false) }
        tvMul.setOnClickListener { appendOnExpression("*", false) }
        tvModul.setOnClickListener { appendOnExpression("%", false) }

        tvClear.setOnClickListener {
            tvInput.text = ""
            tvResult.text = ""
        }

        tvBack.setOnClickListener {
            val string = tvInput.text.toString()
            if (string.isNotEmpty())
                tvInput.text = string.substring(0, string.length - 1)
            tvResult.text = ""
        }

        tvEqual.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvInput.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong() // convert Result into Long
                if (result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()

            } catch (e: Exception) {
                Log.d("Exception", "Message : " + e.message)
            }
        }

    }

    fun appendOnExpression(string: String, canClear: Boolean) {

        // clear Result ifNotEmpty
        if(tvResult.text.isNotEmpty())
            tvInput.text = ""

        if (canClear) {
            tvResult.text = ""
            tvInput.append(string)
        } else {
            tvInput.append(tvResult.text)
            tvInput.append(string)
            tvResult.text = ""
        }
    }
}