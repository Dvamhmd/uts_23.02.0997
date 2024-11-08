package com.example.uts_23020997

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_form)

        val backIcon = findViewById<ImageView>(R.id.back_icon)
        val submitButton = findViewById<Button>(R.id.submit)

        val inputName = findViewById<EditText>(R.id.input_name)
        val inputAge = findViewById<EditText>(R.id.input_age)
        val inputGender = findViewById<RadioGroup>(R.id.input_gender)

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        val savedName = sharedPreferences.getString("NAME","")
        val savedAge = sharedPreferences.getString("AGE","")
        val savedGender = sharedPreferences.getString("GENDER","")

        inputName.setText(savedName)
        inputAge.setText(savedAge)

        if (savedGender == getString(R.string.male)){
            inputGender.check(R.id.male)
        }else if (savedGender == getString(R.string.female)){
            inputGender.check(R.id.female)
        }

        backIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        submitButton.setOnClickListener {
            val genderId = inputGender.checkedRadioButtonId

            val name = inputName.text.toString()
            val age = inputAge.text.toString()
            val ageNumber = age.toIntOrNull()
            val gender = when(genderId){
                R.id.male -> getString(R.string.male)
                R.id.female -> getString(R.string.female)
                else -> getString(R.string.unknown)
            }

            if (age.isEmpty()){
                Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
            }

            if (ageNumber != null) {
                if (name.isNotEmpty() && ageNumber <= 100 && ageNumber > 0 && genderId != -1){

                    val edit = sharedPreferences.edit()
                    edit.putString("NAME",name)
                    edit.putString("AGE",age)
                    edit.putString("GENDER",gender)
                    edit.apply()

                    val intent = Intent(this, ResultActivity::class.java).apply{
                        putExtra("EXTRA_NAME",name)
                        putExtra("EXTRA_AGE",age)
                        putExtra("EXTRA_GENDER",gender)
                    }
                    startActivity(intent)

                }else{
                    Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

}


