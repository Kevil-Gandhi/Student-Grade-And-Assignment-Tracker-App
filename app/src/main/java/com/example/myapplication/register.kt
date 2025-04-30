package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prev_email = intent.getStringExtra("u_email")
        val prev_pass = intent.getStringExtra("u_pass")

        val is_student = intent.getStringExtra("is_student")

        if (is_student.toBoolean())
        {
            val teach = findViewById<RadioButton>(R.id.teacher)

            teach.isChecked = true
        }
        else
        {
            val stud = findViewById<RadioButton>(R.id.student)

            stud.isChecked = true
        }

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val conf_password = findViewById<EditText>(R.id.conf_password)
        val next = findViewById<Button>(R.id.next)

        val stud = findViewById<RadioButton>(R.id.student)

        if (prev_email?.isNotEmpty() == true) {

            email.setText(prev_email)
        }

        if (prev_pass?.isNotEmpty() == true) {

            password.setText(prev_pass)
            conf_password.setText(prev_pass)
        }

        next.setOnClickListener(View.OnClickListener {

            val u_email = email.text.toString()
            val u_pass = password.text.toString()
            val u_con_pass = conf_password.text.toString()

            if (u_email.isNotEmpty() && u_pass.isNotEmpty() && u_con_pass.isNotEmpty()) {

                if (u_pass == u_con_pass) {

                    Toast.makeText(this, "$u_email \n$u_pass", Toast.LENGTH_SHORT).show()

                    if (stud.isChecked) {

                        val intent = Intent(this, register_2::class.java)
                        intent.putExtra("u_email", u_email)
                        intent.putExtra("u_pass", u_pass)
                        startActivity(intent)
                    }
                    else{

                        val intent = Intent(this, reg_teacher::class.java)
                        intent.putExtra("u_email", u_email)
                        intent.putExtra("u_pass", u_pass)
                        startActivity(intent)
                    }

                } else {

                    Toast.makeText(
                        this,
                        "Password & Confirm Password should not be Different...!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {

                Toast.makeText(
                    this,
                    "Enter Email, Password & Confirm Password...!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}