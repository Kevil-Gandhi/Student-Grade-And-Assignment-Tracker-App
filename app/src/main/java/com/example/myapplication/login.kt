package com.example.myapplication

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.Admin.AdminActivity
import com.example.myapplication.Student.student_main
import com.example.myapplication.Teacher.teacher_main
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.log


class login : AppCompatActivity() {

    private val REQUEST_CODE_NOTIFICATION = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            insets
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_CODE_NOTIFICATION
                )
            }
        }

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val link = findViewById<TextView>(R.id.register)

        login.setOnClickListener(View.OnClickListener {

            val u_email = email.text.toString()
            val u_password = password.text.toString()

            if (u_email.isNotEmpty() && u_password.isNotEmpty()) {

                if (u_email == "Admin" && u_password == "Admin") {
                    Toast.makeText(this, "Admin", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,AdminActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
//                    Toast.makeText(this, "$u_email  $u_password", Toast.LENGTH_SHORT).show()

                    user_exist(u_email, u_password)
                }
            } else {

                Toast.makeText(this, "Enter Email & Password...!!", Toast.LENGTH_SHORT).show()
            }
        })

        link.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, register::class.java)
            startActivity(intent)
        })
    }

    private fun showSampleNotification() {
        val channelId = "login_channel_id"
        val notificationId = 1

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Login Channel"
            val descriptionText = "Channel for login notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // ✅ Safe default icon
            .setContentTitle("Login Success")
            .setContentText("Welcome back!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@login,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notify(notificationId, builder.build()) // ✅ This should now work without crash
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_NOTIFICATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Notification permission granted!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission denied. Notifications won’t show.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun user_exist(u_email: String, pass: String) {

        val databaseReference = FirebaseDatabase.getInstance().getReference("Register_info")

        val sanitizedEmail = u_email?.replace("@", "_")?.replace(".", "-");

        if (sanitizedEmail != null) {
            databaseReference.child("Student/" + sanitizedEmail).get().addOnSuccessListener {

                if (it.exists()) {

                    val email = it.child("email").value
                    val password = it.child("password").value
//                    val reg_type = "Student"

                    //                saveUserRole(email.toString(),reg_type.toString())

                    if (password == pass) {

                        val stud = StudentData(
                            it.child("email").value.toString(),
                            it.child("password").value.toString(),
                            it.child("firstName").value.toString(),
                            it.child("lastName").value.toString(),
                            it.child("contact").value.toString(),
                            it.child("gender").value.toString(),
                            it.child("rollNo").value.toString().toInt(),
                            it.child("semester").value.toString().toInt()
                        )

                        saveStudentDetail(stud)
                        showSampleNotification()
                        restartApp()

                        Toast.makeText(
                            this,
//                            "User Exist.....\n$email $password $reg_type ",
                            "Student $stud",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {

                        Toast.makeText(
                            this,
                            "Wrong Password for Student\n'$email'",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } else {

                    Toast.makeText(this, "User Doesn't Exist.....", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {

                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        if (sanitizedEmail != null) {
            databaseReference.child("Teacher/" + sanitizedEmail).get().addOnSuccessListener {

                if (it.exists()) {

                    val email = it.child("email").value
                    val password = it.child("password").value
//                    val reg_type = "Student"

                    //                saveUserRole(email.toString(),reg_type.toString())

                    if (password == pass) {

                        val teach = TeacherData(
                            it.child("email").value.toString(),
                            it.child("password").value.toString(),
                            it.child("firstName").value.toString(),
                            it.child("lastName").value.toString(),
                            it.child("contact").value.toString(),
                            it.child("gender").value.toString(),
                            it.child("qualification").value.toString(),
                            it.child("cources").value.toString()
                        )

                        saveTeacherDetail(teach)
                        showSampleNotification()
                        restartApp()

                        Toast.makeText(
                            this,
//                            "User Exist.....\n$email $password $reg_type ",
                            "Teacher $teach",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {

                        Toast.makeText(
                            this,
                            "Wrong Password for Teacher\n'$email'",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } else {

                    Toast.makeText(this, "User Doesn't Exist.....", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {

                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveTeacherDetail(teach: TeacherData) {

        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("user_role", "Teacher")
        editor.putString("teacher_subject_map", teach.cources)  // change
        editor.putString("teacher", teach.toString())

        editor.putString("email", teach.email)
        editor.putString("password", teach.password)
        editor.putString("name", "${teach.firstName} ${teach.lastName}")
        editor.putString("contact", teach.contact)
        editor.putString("gender", teach.gender)
        editor.putString("qualification", teach.qualification)

        editor.apply()
    }

    private fun saveStudentDetail(stud: StudentData) {

        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("user_role", "Student")
        editor.putString("student_name", stud.firstName + " " + stud.lastName) // change
        editor.putInt("student_semester",stud.semester.toString().toInt())  // change
        editor.putString("student", stud.toString())

        editor.putString("email", stud.email)
        editor.putString("password", stud.password)
        editor.putString("name", "${stud.firstName} ${stud.lastName}")
        editor.putString("contact", stud.contact)
        editor.putString("gender", stud.gender)
        editor.putInt("roll", stud.rollNo.toString().toInt())
        editor.putInt("sem", stud.semester.toString().toInt())

        editor.apply()
    }

    override fun onStart() {
        super.onStart()

        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val userRole = sharedPreferences.getString("user_role", null)
        val student = sharedPreferences.getString("student", null)
        val teacher = sharedPreferences.getString("teacher", null)

        val userType = sharedPreferences.getString("user_role", null)

//        if (userType == "student") {
//            startActivity(Intent(this, student_main::class.java))
//        } else if (userType == "teacher") {
//            startActivity(Intent(this, teacher_main::class.java))
//        } else {
//            startActivity(Intent(this, login::class.java))
//        }

        var intent: Intent = Intent(this, login::class.java)
        if (userRole != null) {
            intent = Intent(this, login::class.java)
            when (userRole) {
                "Student" -> {
                    intent = Intent(this, student_main::class.java)
//                    intent.putExtra("student",student.toString())

//                    startActivity(intent)
                    Toast.makeText(this, "Student\n$student", Toast.LENGTH_SHORT).show()

//                    openStudentMainPage()
                }

                "Teacher" -> {
                    intent = Intent(this, teacher_main::class.java)
//                    intent.putExtra("teacher",teacher.toString())

//                    startActivity(intent)

                    Toast.makeText(this, "Teacher\n$teacher", Toast.LENGTH_SHORT).show()
                }
            }

            startActivity(intent)
            finish()
        }
    }

    private fun restartApp() {
        val intent = baseContext.packageManager.getLaunchIntentForPackage(baseContext.packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finishAffinity() // Close all previous activities
    }

    private fun openStudentMainPage() {

        try {

//            val intent = Intent(this, Class.forName("com.example.student.ui.StudentMainPage"))

//            startActivity(intent)
//            finish()
        } catch (e: Exception) {

            e.printStackTrace()
            Log.e("LoginActivity", "Error in openStudentMainPage()", e)
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

}


