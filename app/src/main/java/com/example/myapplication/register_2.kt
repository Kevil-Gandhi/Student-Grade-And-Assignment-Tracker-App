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
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class register_2 : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
//    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

//            firebaseAuth = FirebaseAuth.getInstance()

            insets
        }

//        val prev_intent = intent
        var u_email = intent.getStringExtra("u_email")
        val u_pass = intent.getStringExtra("u_pass")

        databaseReference = FirebaseDatabase.getInstance().getReference("Register_info");

        Toast.makeText(this, "$u_email \n$u_pass", Toast.LENGTH_SHORT).show()
        
        val sem = findViewById<Spinner>(R.id.sem)
        val sem_array = arrayOf(" Select Semester ", "1", "2", "3", "4", "5", "6")
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            sem_array
        )

        sem.adapter = adapter

        var u_sem = sem_array.get(1)

        sem.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                u_sem = sem_array[position]
                Toast.makeText(this@register_2, u_sem, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val fname = findViewById<EditText>(R.id.fname)
        val lname = findViewById<EditText>(R.id.lname)
        val contact = findViewById<EditText>(R.id.contact)
        val male = findViewById<RadioButton>(R.id.male)
        val roll = findViewById<EditText>(R.id.rollno)

        val next = findViewById<Button>(R.id.reg)
        val prev = findViewById<Button>(R.id.prev)

        prev.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, register::class.java)
            intent.putExtra("u_email",u_email)
            intent.putExtra("u_pass",u_pass)
            intent.putExtra("is_student",true)
            startActivity(intent)

        })

        next.setOnClickListener(View.OnClickListener {

            val u_fname = fname.text.toString()
            val u_lname = lname.text.toString()
            val u_cont = contact.text.toString()
            var u_gender = "Male"
            val u_roll = roll.text.toString()

            if (!male.isChecked) {
                u_gender = "Female"
            }

            if (u_fname.isNotEmpty() && u_lname.isNotEmpty() && u_cont.isNotEmpty() && u_gender.isNotEmpty() && u_roll.isNotEmpty()) {

                if (u_cont.length == 10) {

                    if (u_sem != sem_array.get(0)) {

                        Toast.makeText(
                            this,
                            "$u_fname\n$u_lname\n$u_cont\n$u_gender\n$u_roll\n$u_sem",
                            Toast.LENGTH_SHORT
                        ).show()

                        val u_email_replace = u_email?.replace("@","_")?.replace(".","-");
                        val stud = StudentData(u_email_replace.toString(),u_pass.toString(),u_fname,u_lname,u_cont,u_gender,u_roll.toIntOrNull(),u_sem.toIntOrNull());

                        databaseReference.child("Student/"+u_email_replace.toString()).setValue(stud).addOnSuccessListener {

                            Toast.makeText(this, "Student Registered Successfully...!!", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this,login::class.java)
                            startActivity(intent)
                            finish()

                        }.addOnFailureListener{

                            Toast.makeText(this, "Student Registered Failed...!!", Toast.LENGTH_SHORT).show()
                        }

                    } else {

                        Toast.makeText(this, "Select Semester...!!", Toast.LENGTH_SHORT).show()
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