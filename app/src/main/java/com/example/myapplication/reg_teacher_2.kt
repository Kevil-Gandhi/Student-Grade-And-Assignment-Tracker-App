package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class reg_teacher_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reg_teacher2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prev_email = intent.getStringExtra("u_email")
        val prev_pass = intent.getStringExtra("u_pass")
        val prev_fname = intent.getStringExtra("u_fname")
        val prev_lname = intent.getStringExtra("u_lname")
        val prev_con = intent.getStringExtra("u_contact")
        val prev_gender = intent.getStringExtra("u_gender")
        val prev_qua = intent.getStringExtra("u_qualification")

        Toast.makeText(
            this,
            "$prev_email\n$prev_pass\n" + "$prev_fname\n" + "$prev_lname\n" + "$prev_con\n" + "$prev_gender\n" + "$prev_qua",
            Toast.LENGTH_SHORT
        ).show()

        val databaseReference = FirebaseDatabase.getInstance().getReference("Register_info");


        val lay_sem1 = findViewById<LinearLayout>(R.id.lay_sem_1)
        val lay_sem2 = findViewById<LinearLayout>(R.id.lay_sem_2)
        val lay_sem3 = findViewById<LinearLayout>(R.id.lay_sem_3)
        val lay_sem4 = findViewById<LinearLayout>(R.id.lay_sem_4)
        val lay_sem5 = findViewById<LinearLayout>(R.id.lay_sem_5)
        val lay_sem6 = findViewById<LinearLayout>(R.id.lay_sem_6)

        val sem1 = findViewById<CheckBox>(R.id.sem_1)
        val sem2 = findViewById<CheckBox>(R.id.sem_2)
        val sem3 = findViewById<CheckBox>(R.id.sem_3)
        val sem4 = findViewById<CheckBox>(R.id.sem_4)
        val sem5 = findViewById<CheckBox>(R.id.sem_5)
        val sem6 = findViewById<CheckBox>(R.id.sem_6)

        val sem1_sub1 = findViewById<CheckBox>(R.id.sem1_sub1)
        val sem1_sub2 = findViewById<CheckBox>(R.id.sem1_sub2)
        val sem1_sub3 = findViewById<CheckBox>(R.id.sem1_sub3)
        val sem1_sub4 = findViewById<CheckBox>(R.id.sem1_sub4)
        val sem1_sub5 = findViewById<CheckBox>(R.id.sem1_sub5)

        val sem2_sub1 = findViewById<CheckBox>(R.id.sem2_sub1)
        val sem2_sub2 = findViewById<CheckBox>(R.id.sem2_sub2)
        val sem2_sub3 = findViewById<CheckBox>(R.id.sem2_sub3)
        val sem2_sub4 = findViewById<CheckBox>(R.id.sem2_sub4)
        val sem2_sub5 = findViewById<CheckBox>(R.id.sem2_sub5)

        val sem3_sub1 = findViewById<CheckBox>(R.id.sem3_sub1)
        val sem3_sub2 = findViewById<CheckBox>(R.id.sem3_sub2)
        val sem3_sub3 = findViewById<CheckBox>(R.id.sem3_sub3)
        val sem3_sub4 = findViewById<CheckBox>(R.id.sem3_sub4)
        val sem3_sub5 = findViewById<CheckBox>(R.id.sem3_sub5)

        val sem4_sub1 = findViewById<CheckBox>(R.id.sem4_sub1)
        val sem4_sub2 = findViewById<CheckBox>(R.id.sem4_sub2)
        val sem4_sub3 = findViewById<CheckBox>(R.id.sem4_sub3)
        val sem4_sub4 = findViewById<CheckBox>(R.id.sem4_sub4)
        val sem4_sub5 = findViewById<CheckBox>(R.id.sem4_sub5)

        val sem5_sub1 = findViewById<CheckBox>(R.id.sem5_sub1)
        val sem5_sub2 = findViewById<CheckBox>(R.id.sem5_sub2)
        val sem5_sub3 = findViewById<CheckBox>(R.id.sem5_sub3)
        val sem5_sub4 = findViewById<CheckBox>(R.id.sem5_sub4)
        val sem5_sub5 = findViewById<CheckBox>(R.id.sem5_sub5)

        val sem6_sub1 = findViewById<CheckBox>(R.id.sem6_sub1)
        val sem6_sub2 = findViewById<CheckBox>(R.id.sem6_sub2)
        val sem6_sub3 = findViewById<CheckBox>(R.id.sem6_sub3)
        val sem6_sub4 = findViewById<CheckBox>(R.id.sem6_sub4)
        val sem6_sub5 = findViewById<CheckBox>(R.id.sem6_sub5)


        val prev_button = findViewById<Button>(R.id.prev)
        val reg_button = findViewById<Button>(R.id.reg)

        prev_button.setOnClickListener(View.OnClickListener {
            finish()
        })

        reg_button.setOnClickListener(View.OnClickListener {

            var subjects:String="";

            var checkbox=ArrayList<CheckBox>();

            checkbox.add(sem1_sub1);
            checkbox.add(sem1_sub2);
            checkbox.add(sem1_sub3);
            checkbox.add(sem1_sub4);
            checkbox.add(sem1_sub5);

            checkbox.add(sem2_sub1);
            checkbox.add(sem2_sub2);
            checkbox.add(sem2_sub3);
            checkbox.add(sem2_sub4);
            checkbox.add(sem2_sub5);

            checkbox.add(sem3_sub1);
            checkbox.add(sem3_sub2);
            checkbox.add(sem3_sub3);
            checkbox.add(sem3_sub4);
            checkbox.add(sem3_sub5);

            checkbox.add(sem4_sub1);
            checkbox.add(sem4_sub2);
            checkbox.add(sem4_sub3);
            checkbox.add(sem4_sub4);
            checkbox.add(sem4_sub5);

            checkbox.add(sem5_sub1);
            checkbox.add(sem5_sub2);
            checkbox.add(sem5_sub3);
            checkbox.add(sem5_sub4);
            checkbox.add(sem5_sub5);

            checkbox.add(sem6_sub1);
            checkbox.add(sem6_sub2);
            checkbox.add(sem6_sub3);
            checkbox.add(sem6_sub4);
            checkbox.add(sem6_sub5);


            for (check in checkbox)
            {
                if(check.isChecked)
                {
                    subjects = subjects + " " + check.text.toString()
                }
            }

            val email = prev_email?.replace("@","_")?.replace(".","-");
            val teacher = TeacherData(email.toString(),prev_pass.toString(),prev_fname.toString(),prev_lname.toString(),prev_con.toString(),prev_gender.toString(),prev_qua.toString(),subjects);

            databaseReference.child("Teacher/"+email.toString()).setValue(teacher).addOnSuccessListener {

                Toast.makeText(this, "Teacher Registered Successfully...!!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this,login::class.java)
                startActivity(intent)
                finish()

            }.addOnFailureListener{

                Toast.makeText(this, "Teacher Registered Failed...!!", Toast.LENGTH_SHORT).show()
            }
        })

//        sem1.setOnClickListener(View.OnClickListener {
//
//            lay_sem1.visibility = View.VISIBLE
//        })
//
//        sem2.setOnClickListener(View.OnClickListener {
//
//            lay_sem2.visibility = View.VISIBLE
//        })
//
//        sem3.setOnClickListener(View.OnClickListener {
//
//            lay_sem3.visibility = View.VISIBLE
//        })

        sem1.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                lay_sem1.visibility = LinearLayout.VISIBLE

                lay_sem2.visibility = View.GONE
                sem2.isChecked = false

                lay_sem3.visibility = View.GONE
                sem3.isChecked = false

                lay_sem4.visibility = View.GONE
                sem4.isChecked = false

                lay_sem5.visibility = View.GONE
                sem5.isChecked = false

                lay_sem6.visibility = View.GONE
                sem6.isChecked = false

            } else {
                LinearLayout.GONE
            }
        }

        sem2.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                lay_sem2.visibility = LinearLayout.VISIBLE

                lay_sem1.visibility = View.GONE
                sem1.isChecked = false

                lay_sem3.visibility = View.GONE
                sem3.isChecked = false

                lay_sem4.visibility = View.GONE
                sem4.isChecked = false

                lay_sem5.visibility = View.GONE
                sem5.isChecked = false

                lay_sem6.visibility = View.GONE
                sem6.isChecked = false
            } else {
                LinearLayout.GONE
            }
        }

        sem3.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                lay_sem3.visibility = LinearLayout.VISIBLE

                lay_sem2.visibility = View.GONE
                sem2.isChecked = false

                lay_sem1.visibility = View.GONE
                sem1.isChecked = false

                lay_sem4.visibility = View.GONE
                sem4.isChecked = false

                lay_sem5.visibility = View.GONE
                sem5.isChecked = false

                lay_sem6.visibility = View.GONE
                sem6.isChecked = false

            } else {
                LinearLayout.GONE
            }
        }

        sem4.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                lay_sem4.visibility = LinearLayout.VISIBLE

                lay_sem2.visibility = View.GONE
                sem2.isChecked = false

                lay_sem1.visibility = View.GONE
                sem1.isChecked = false

                lay_sem3.visibility = View.GONE
                sem3.isChecked = false

                lay_sem5.visibility = View.GONE
                sem5.isChecked = false

                lay_sem6.visibility = View.GONE
                sem6.isChecked = false

            } else {
                LinearLayout.GONE
            }
        }

        sem5.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                lay_sem5.visibility = LinearLayout.VISIBLE

                lay_sem2.visibility = View.GONE
                sem2.isChecked = false

                lay_sem1.visibility = View.GONE
                sem1.isChecked = false

                lay_sem4.visibility = View.GONE
                sem4.isChecked = false

                lay_sem3.visibility = View.GONE
                sem3.isChecked = false

                lay_sem6.visibility = View.GONE
                sem6.isChecked = false

            } else {
                LinearLayout.GONE
            }
        }

        sem6.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                lay_sem6.visibility = LinearLayout.VISIBLE

                lay_sem2.visibility = View.GONE
                sem2.isChecked = false

                lay_sem1.visibility = View.GONE
                sem1.isChecked = false

                lay_sem4.visibility = View.GONE
                sem4.isChecked = false

                lay_sem5.visibility = View.GONE
                sem5.isChecked = false

                lay_sem3.visibility = View.GONE
                sem3.isChecked = false

            } else {
                LinearLayout.GONE
            }
        }
    }

}