package com.example.eulersphi

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EulersPhiActivity : Activity() {
    private fun gcd(a: Int, b: Int): Int {
        var aTmp = a
        var bTmp = b

        while (bTmp != 0) {
            if (bTmp < aTmp) {
                val t = bTmp
                bTmp = aTmp
                aTmp = t
            }

            bTmp %= aTmp
        }

        return aTmp
    }

    private fun eulersPhi(n: Int): Int {
        var cnt = 1

        for (curr: Int in 1 until n-1) {
            if (gcd(n, curr) == 1) {
                cnt += 1
            }
        }

        return cnt
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eulers_phi)

        val submitButton = findViewById<Button>(R.id.submit)
        val numberEditText = findViewById<EditText>(R.id.number)
        val resultTextView = findViewById<TextView>(R.id.result)

        submitButton.setOnClickListener {
            val number = Integer.parseInt(numberEditText.text.toString())
            resultTextView.text = "Result: ${eulersPhi(number)}"
        }
    }
}