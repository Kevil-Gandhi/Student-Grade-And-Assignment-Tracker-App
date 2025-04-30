package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.emptyLongSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.Student.student_main
import com.example.myapplication.Teacher.teacher_main
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class changePass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_change_pass)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val previousPassword: EditText = findViewById(R.id.previousPassword)
        val sp: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val back: ImageView = findViewById(R.id.back)
        val first: LinearLayout = findViewById(R.id.step1)
        val second: LinearLayout = findViewById(R.id.step2)
        val third: LinearLayout = findViewById(R.id.step3)
        val submit: Button = findViewById(R.id.submit)
        val newPassword: TextInputEditText = findViewById(R.id.newPassword)
        val confirmPassword: TextInputEditText = findViewById(R.id.confirmPassword)
        val path: String = intent.getStringExtra("path").toString();
        if (path.isNullOrEmpty()) {
            Toast.makeText(this, "Invalid path", Toast.LENGTH_SHORT).show()
            return
        }

        val check: Button = findViewById(R.id.check)
        second.visibility = View.GONE
        third.visibility = View.GONE

        submit.setOnClickListener {
            if (newPassword.text.toString() == sp.getString("password", "")) {
                Toast.makeText(this, "You can't enter old password", Toast.LENGTH_SHORT).show()
            } else if (newPassword.text.toString() != confirmPassword.text.toString()) {
                Toast.makeText(this, "Confirm password is different", Toast.LENGTH_SHORT).show()
            } else {
                val dr: DatabaseReference = FirebaseDatabase.getInstance().getReference(path)
                val email = sp.getString("email", null)
                val safeEmail: String;
                if (email != null) {
                    safeEmail = getSafeEmailKey(email)
                    val updates = HashMap<String, Any>()
                    updates["password"] = newPassword.text.toString()
                    dr.child(safeEmail).updateChildren(updates).addOnSuccessListener {
                        val editor = sp.edit()
                        editor.putString("password", newPassword.text.toString())

                        val r = intent.getStringExtra("r")
                        var intent: Intent

                        if (r == "Student") {
                            intent = Intent(this, student_main::class.java)
                        } else {
                            intent = Intent(this, teacher_main::class.java)
                        }

                        intent.putExtra("email", sp.getString("email", ""))
                        intent.putExtra("role", sp.getString("role", ""))

                        editor.apply()
                        startActivity(intent)

                        Toast.makeText(this, "Password Changed", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Email not found in SharedPreferences", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        back.setOnClickListener {
            finish()
        }

        check.setOnClickListener {
            val invalidpass: LinearLayout = findViewById(R.id.invalidPassword)
            if (previousPassword.text.toString() == sp.getString("password", "")) {
                Handler(Looper.getMainLooper()).postDelayed({
                }, 1500)
                first.visibility = View.GONE
                second.visibility = View.VISIBLE
                third.visibility = View.VISIBLE

                invalidpass.visibility = View.GONE
            } else {
                invalidpass.visibility = View.VISIBLE
            }
        }
    }

    private fun getSafeEmailKey(email: String): String {
        return email.replace("@", "_").replace(".", "-")
    }
}
