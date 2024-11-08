package com.example.uts_23020997

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity(){

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

        val resultName = findViewById<TextView>(R.id.name_result)
        val resultAge = findViewById<TextView>(R.id.age_result)
        val resultGender = findViewById<TextView>(R.id.gender_result)

        val name = intent.getStringExtra("EXTRA_NAME")
        val age = intent.getStringExtra("EXTRA_AGE")
        val gender = intent.getStringExtra("EXTRA_GENDER")

        resultName.text = name
        resultAge.text = age
        resultGender.text = gender

        val backIcon = findViewById<ImageView>(R.id.back_icon)
        backIcon.setOnClickListener {
            val intent = Intent(this, RegistrationFormActivity::class.java)
            startActivity(intent)
        }
    }
}