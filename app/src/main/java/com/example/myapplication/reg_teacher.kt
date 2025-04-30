package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class reg_teacher : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reg_teacher)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val u_email = intent.getStringExtra("u_email")
        val u_pass = intent.getStringExtra("u_pass")

        Toast.makeText(this, "$u_email \n$u_pass", Toast.LENGTH_SHORT).show()

        val next = findViewById<Button>(R.id.reg)
        val prev = findViewById<Button>(R.id.prev)

        prev.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, register::class.java)
            intent.putExtra("u_email", u_email)
            intent.putExtra("u_pass", u_pass)
            startActivity(intent)

        })

        val quali = findViewById<Spinner>(R.id.qualification)
        val quali_array = arrayOf(" Select Qualification ", "PHD", "Post Greduate", "Greduate")
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            quali_array
        )

        quali.adapter = adapter

        var u_qua = quali_array.get(1)

        quali.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                u_qua = quali_array[position]
                Toast.makeText(this@reg_teacher, u_qua, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val fname = findViewById<EditText>(R.id.fname)
        val lname = findViewById<EditText>(R.id.lname)
        val contact = findViewById<EditText>(R.id.contact)
        val male = findViewById<RadioButton>(R.id.male)

        next.setOnClickListener(View.OnClickListener {

            val u_fname = fname.text.toString()
            val u_lname = lname.text.toString()
            val u_cont = contact.text.toString()
            var u_gender = "Male"

            if (!male.isChecked) {
                u_gender = "Female"
            }

            if (u_fname.isNotEmpty() && u_lname.isNotEmpty() && u_cont.isNotEmpty() && u_gender.isNotEmpty()) {

                if (u_cont.length == 10) {

                    if (u_qua != quali_array.get(0)) {

                        Toast.makeText(
                            this,
                            "$u_fname\n$u_lname\n$u_cont\n$u_gender\n$u_qua",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this,reg_teacher_2::class.java)

                        intent.putExtra("u_email", u_email)
                        intent.putExtra("u_pass", u_pass)
                        intent.putExtra("u_fname",u_fname)
                        intent.putExtra("u_lname",u_lname)
                        intent.putExtra("u_contact",u_cont)
                        intent.putExtra("u_gender",u_gender)
                        intent.putExtra("u_qualification",u_qua)

                        startActivity(intent)

                    } else {

                        Toast.makeText(this, "Select Qualification...!!", Toast.LENGTH_SHORT).show()
                    }
                } else {

                    Toast.makeText(
                        this,
                        "Contact no. shouldn't be less or grater than 10 Digit...!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {

                Toast.makeText(this, "Input Field should not be EMPTY...!!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}