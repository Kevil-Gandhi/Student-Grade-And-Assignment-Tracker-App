//package com.example.myapplication.Teacher
//
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.webkit.WebResourceRequest
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import android.widget.ImageButton
//import android.widget.LinearLayout
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.myapplication.R
//
//class teacher_main : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_teacher_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//
////            val webView:WebView = findViewById(R.id.webView1)
////
////            webView.webViewClient = WebViewClient() // Ensure page opens in WebView
////            webView.settings.javaScriptEnabled = true // Enable JavaScript if required
////
////            webView.webViewClient = object : WebViewClient() {
////                override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
////                    Log.e("WebViewError", "Error: $description, Code: $errorCode")
////                }
////            }
////
////
////            webView.loadUrl("http://temp.priyatal.buzz/")
//
//            insets
//        }
//
//        val img_btn1: ImageButton = findViewById(R.id.imageButton1)
//        val img_btn2: ImageButton = findViewById(R.id.imageButton2)
//        val img_btn3: ImageButton = findViewById(R.id.imageButton3)
//
//        val sec1: LinearLayout = findViewById(R.id.section1)
//        val sec2: LinearLayout = findViewById(R.id.section2)
//        val sec3: LinearLayout = findViewById(R.id.section3)
//
//        val webView1:WebView = findViewById(R.id.webView1)
//        val webView2:WebView = findViewById(R.id.webView2)
//
//        webView1.settings.javaScriptEnabled = true
//        webView1.webViewClient = WebViewClient()
//
////        webView1.settings.allowFileAccess = true
////        webView1.settings.allowContentAccess = true
////        webView1.settings.javaScriptEnabled = true
//
//        webView1.loadUrl("https://temp2.priyatal.buzz/upload.php")
//
//        webView2.settings.javaScriptEnabled = true
//        webView2.webViewClient = WebViewClient()
//
//        webView2.loadUrl("https://temp2.priyatal.buzz/teacher.php")
//
//        sec1.visibility = View.VISIBLE
//        sec2.visibility = View.GONE
//        sec3.visibility = View.GONE
//
//        img_btn1.setOnClickListener(View.OnClickListener {
//            sec1.visibility = View.VISIBLE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.GONE
//
//            webView1.loadUrl("https://temp2.priyatal.buzz/upload.php")
//        })
//
//        img_btn2.setOnClickListener(View.OnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.VISIBLE
//            sec3.visibility = View.GONE
//
//            webView2.loadUrl("https://temp2.priyatal.buzz/teacher.php")
//        })
//
//        img_btn3.setOnClickListener(View.OnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.VISIBLE
//        })
//
//    }
//}

//package com.example.myapplication.Teacher
//
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.view.View
//import android.webkit.WebResourceRequest
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import android.widget.ImageButton
//import android.widget.LinearLayout
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.myapplication.R
//
//class teacher_main : AppCompatActivity() {
//
//    private lateinit var webView1: WebView
//    private lateinit var webView2: WebView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_teacher_main)
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        val img_btn1: ImageButton = findViewById(R.id.imageButton1)
//        val img_btn2: ImageButton = findViewById(R.id.imageButton2)
//        val img_btn3: ImageButton = findViewById(R.id.imageButton3)
//
//        val sec1: LinearLayout = findViewById(R.id.section1)
//        val sec2: LinearLayout = findViewById(R.id.section2)
//        val sec3: LinearLayout = findViewById(R.id.section3)
//
//        webView1 = findViewById(R.id.webView1)
//        webView2 = findViewById(R.id.webView2)
//
//        // Setup both WebViews
//        setupWebView(webView1)
//        setupWebView(webView2)
//
//        // Load initial pages
//        webView1.loadUrl("https://temp2.priyatal.buzz/upload.php")
//        webView2.loadUrl("https://temp2.priyatal.buzz/teacher.php")
//
//        sec1.visibility = View.VISIBLE
//        sec2.visibility = View.GONE
//        sec3.visibility = View.GONE
//
//        img_btn1.setOnClickListener {
//            sec1.visibility = View.VISIBLE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.GONE
//            webView1.loadUrl("https://temp2.priyatal.buzz/upload.php")
//        }
//
//        img_btn2.setOnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.VISIBLE
//            sec3.visibility = View.GONE
//            webView2.loadUrl("https://temp2.priyatal.buzz/teacher.php")
//        }
//
//        img_btn3.setOnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.VISIBLE
//        }
//    }
//
//    // WebView setup with PDF handling
//    private fun setupWebView(webView: WebView) {
//        webView.settings.apply {
//            javaScriptEnabled = true
//            domStorageEnabled = true
//            allowFileAccess = true
//            allowContentAccess = true
//            loadWithOverviewMode = true
//            useWideViewPort = true
//        }
//
//        webView.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//                val url = request?.url.toString()
//
//                if (url.endsWith(".pdf")) {
//                    openPdf(url)  // Open PDFs
//                    return true
//                }
//                return false
//            }
//        }
//    }
//
//    // Open PDF in Google Docs Viewer or external viewer
//    private fun openPdf(pdfUrl: String) {
//        try {
//            // Try Google Docs Viewer first
//            val googleDocsUrl = "https://docs.google.com/gview?embedded=true&url=$pdfUrl"
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(googleDocsUrl))
//            startActivity(intent)
//        } catch (e: Exception) {
//            // Fallback to external PDF viewer
//            try {
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.setDataAndType(Uri.parse(pdfUrl), "application/pdf")
//                intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
//                startActivity(intent)
//            } catch (e: Exception) {
//                Toast.makeText(this, "No PDF viewer available", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    // Handle back navigation for WebView
//    override fun onBackPressed() {
//        when {
//            findViewById<LinearLayout>(R.id.section1).visibility == View.VISIBLE && webView1.canGoBack() -> webView1.goBack()
//            findViewById<LinearLayout>(R.id.section2).visibility == View.VISIBLE && webView2.canGoBack() -> webView2.goBack()
//            else -> super.onBackPressed()
//        }
//    }
//}
//

package com.example.myapplication.Teacher

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences.Editor
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Notices
import com.example.myapplication.R
import com.example.myapplication.TeacherData
import com.example.myapplication.changePass
import com.example.myapplication.login
import com.example.myapplication.noticeAdapter
import com.example.myapplication.viewNotices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONObject
import java.net.URLEncoder
import java.nio.file.Files

class teacher_main : AppCompatActivity() {

    private var filePathCallback: ValueCallback<Array<Uri>>? = null
    private val FILE_REQUEST_CODE = 100

    private lateinit var webView1: WebView
    private lateinit var webView2: WebView

    lateinit var semesterJson: JSONObject


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_teacher_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
// ***************
       val database = FirebaseDatabase.getInstance().getReference("Register_info/Student")
       val semesterCounts = mutableMapOf<Int, Int>()

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (studentSnapshot in snapshot.children) {
                    val semester = studentSnapshot.child("semester").getValue(Int::class.java)

                    if (semester != null) {
                        semesterCounts[semester] = semesterCounts.getOrDefault(semester, 0) + 1
                    }
                }

                // Print or use the result
                for ((semester, count) in semesterCounts) {

                    semesterJson = JSONObject()
                    for ((semester, count) in semesterCounts) {
                        semesterJson.put("Sem$semester", count)
                    }

                    Log.d("SemesterCount", "Semester $semester: $count students")
                }

                // 👉 Send this data to your PHP if needed using URL or API
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Database error: ${error.message}")
            }
        })
// ***************

        requestStoragePermission()

        val notice = findViewById<FloatingActionButton>(R.id.fab)
        notice.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "Notice", Toast.LENGTH_SHORT).show()

            val intent = Intent(this,viewNotices::class.java)
            startActivity(intent)

        })

        val logoutBtn = findViewById<FloatingActionButton>(R.id.fab2)

//        logoutBtn.setOnClickListener {
//            val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//            editor.clear() // Clears all stored values
//            editor.apply()
//
//            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
//
//            // Redirect to login screen and clear back stack
//            val intent = Intent(this, login::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//            finish()
//        }

        logoutBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Logout")
            builder.setMessage("Are you sure you want to logout?")
            builder.setPositiveButton("Yes") { dialog, _ ->
                // Clear SharedPreferences
                val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()

                Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

                // Redirect to login screen
                val intent = Intent(this, login::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()

                dialog.dismiss()
            }
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss() // Close the dialog
            }
            val dialog = builder.create()
            dialog.show()
        }

        val assLayout = findViewById<LinearLayout>(R.id.section1)
        val gradeLayout = findViewById<LinearLayout>(R.id.section2)
        val profileLayout = findViewById<LinearLayout>(R.id.section3)

        val assignment:LinearLayout = findViewById(R.id.assignment)
        val grade:LinearLayout = findViewById(R.id.grade)
        val profile:LinearLayout = findViewById(R.id.profile)

        assLayout.visibility = View.VISIBLE
        gradeLayout.visibility = View.GONE
        profileLayout.visibility = View.GONE

        assignment.setOnClickListener{
            assLayout.visibility = View.VISIBLE
            gradeLayout.visibility = View.GONE
            profileLayout.visibility = View.GONE

            assignment.setBackgroundColor(ContextCompat.getColor(this, R.color.BlueGreen))
            grade.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))
            profile.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))

            loadurl()
        }

        grade.setOnClickListener{
            assLayout.visibility = View.GONE
            gradeLayout.visibility = View.VISIBLE
            profileLayout.visibility = View.GONE

            assignment.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))
            grade.setBackgroundColor(ContextCompat.getColor(this, R.color.BlueGreen))
            profile.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))

            loadurl()
        }

        profile.setOnClickListener{
            assLayout.visibility = View.GONE
            gradeLayout.visibility = View.GONE
            profileLayout.visibility = View.VISIBLE

            assignment.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))
            grade.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))
            profile.setBackgroundColor(ContextCompat.getColor(this, R.color.BlueGreen))

            loadurl()
        }

        webView1 = findViewById(R.id.webView1)
        webView2 = findViewById(R.id.webView2)

//        setupWebView(webView1, "https://temp2.priyatal.buzz/upload.php")

//        setupWebView(webView1, "http://kevil.infinityfreeapp.com/upload.php") // remember

        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val subjects = sharedPreferences.getString("teacher_subject_map", null)

        if (subjects != null) {
            val encodedSubjects = URLEncoder.encode(subjects, "UTF-8")
//            val url = "http://kevil.infinityfreeapp.com/teacher2.php?subjects=$encodedSubjects"     //  remember
//            val url = "http://kevil.infinityfreeapp.com/teacher4.php?subjects=$encodedSubjects"
            val url = "http://kevil.infinityfreeapp.com/teacher5.php?subjects=$encodedSubjects"
//            val url2 = "http://kevil.infinityfreeapp.com/grade_assignment.php?subjects=$encodedSubjects"
//            val url2 = "http://kevil.infinityfreeapp.com/grade_assignment2.php?subjects=$encodedSubjects"
            val url2 = "http://kevil.infinityfreeapp.com/grade_assignment3.php?subjects=$encodedSubjects"

//            setupWebView(webView1, url)
            setupWebView(webView1, url)
            setupWebView(webView2, url2)
            Toast.makeText(this, "Subjects: $subjects", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No subjects found in SharedPreferences", Toast.LENGTH_SHORT)
                .show()
        }

//        val url = "http://yourdomain.com/teacher.php?subjects=$encodedSubjects"

//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//        startActivity(intent)


//        setupWebView(webView2, "https://temp2.priyatal.buzz/teacher.php")
//        setupWebView(webView2, "http://kevil.infinityfreeapp.com/teacher.php")
//        setupWebView(webView2, "http://kevil.infinityfreeapp.com/view_submission.php")
//        setupWebView(webView2, "http://kevil.infinityfreeapp.com/view_submission2.php")
//        setupWebView(webView2, "http://kevil.infinityfreeapp.com/view_submission2.php")     //  Remember

//        val img_btn1: ImageButton = findViewById(R.id.imageButton1)
//        val img_btn2: ImageButton = findViewById(R.id.imageButton2)
//        val img_btn3: ImageButton = findViewById(R.id.imageButton3)
//
//        val sec1: LinearLayout = findViewById(R.id.section1)
//        val sec2: LinearLayout = findViewById(R.id.section2)
//        val sec3: LinearLayout = findViewById(R.id.section3)
//
//        sec1.visibility = View.VISIBLE
//        sec2.visibility = View.GONE
//        sec3.visibility = View.GONE
//
//        img_btn1.setOnClickListener {
//            sec1.visibility = View.VISIBLE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.GONE
//
//            loadurl()
//        }
//
//        img_btn2.setOnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.VISIBLE
//            sec3.visibility = View.GONE
//
//            loadurl()
//        }
//
//        img_btn3.setOnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.VISIBLE
//        }

        Toast.makeText(this, "Teacher", Toast.LENGTH_SHORT).show()

        val emailtxt = findViewById<TextView>(R.id.emailTxt)
        val nametxt = findViewById<TextView>(R.id.nameTxt)
        val contacttxt = findViewById<TextView>(R.id.contactTxt)
        val gendertxt = findViewById<TextView>(R.id.genderTxt)
        val qualTxt = findViewById<TextView>(R.id.quaTxt)
        val roleTxt = findViewById<TextView>(R.id.roleTxt)
        val nameHeading = findViewById<TextView>(R.id.nameHeading)
        val emailHeading = findViewById<TextView>(R.id.emailHeading)

//        val sp:Editor = sharedPreferences.edit();

        val email = sharedPreferences.getString("email", "")?.replace("_", "@")?.replace("-", ".");
        emailtxt.text = email
        nametxt.text = sharedPreferences.getString("name", "")
        contacttxt.text = sharedPreferences.getString("contact", "")
        gendertxt.text = sharedPreferences.getString("gender", "")
        qualTxt.text = sharedPreferences.getString("qualification", "")
        roleTxt.text = sharedPreferences.getString("user_role", "")
        nameHeading.text = sharedPreferences.getString("name", "")
        emailHeading.text = sharedPreferences.getString("email", "")

        val btnEmail: ImageView = findViewById(R.id.btn_email)
        val btnName: ImageView = findViewById(R.id.nameBtn)
        val btnContact: ImageView = findViewById(R.id.contactBtn)
        val btnGender: ImageView = findViewById(R.id.genderBtn)
        val btnQuali: ImageView = findViewById(R.id.quaBtn)

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.updatepage)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val heading: TextView = dialog.findViewById(R.id.updateHeading)
        val oldData: EditText = dialog.findViewById(R.id.oldData)
        val newData: EditText = dialog.findViewById(R.id.newData)
        val cancel: Button = dialog.findViewById(R.id.cancelBtn)
        val update: Button = dialog.findViewById(R.id.updateBtn)

        cancel.setOnClickListener {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        btnEmail.setOnClickListener {
            oldData.setText(emailtxt.text.toString())
            heading.text = "Email"
            newData.setText("")
            dialog.show()
        }

        btnName.setOnClickListener {
            oldData.setText(nametxt.text.toString())
            heading.text = "Name"
            newData.setText("")
            dialog.show()
        }

        btnGender.setOnClickListener {
            oldData.setText(gendertxt.text.toString())
            heading.text = "Gender"
            newData.setText("")
            dialog.show()
        }

        btnContact.setOnClickListener {
            oldData.setText(contacttxt.text.toString())
            heading.text = "Contact"
            newData.setText("")
            dialog.show()
        }

        btnQuali.setOnClickListener {
            oldData.setText(qualTxt.text.toString())
            heading.text = "Qualification"
            newData.setText("")
            dialog.show()
        }

        val path = "Register_info/Teacher";

        update.setOnClickListener {
            val field = heading.text.toString()
            val updatedValue = newData.text.toString()
            val sp = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).edit()
            val oldEmailKey = emailtxt.text.toString()
            val safeOldEmailKey = getSafeEmailKey(oldEmailKey)
            val updates = HashMap<String, Any>()

            val dr: DatabaseReference = FirebaseDatabase.getInstance().getReference(path)

            if (field == "Email") {
                updates[field.lowercase()] = updatedValue
                val newEmailKey = getSafeEmailKey(updatedValue)
                dr.child(safeOldEmailKey).get().addOnSuccessListener { it ->
                    if (it.exists()) {

                        var teacher = TeacherData(
                            it.child("email").value?.toString() ?: "",
                            it.child("password").value?.toString() ?: "",
                            it.child("firstName").value?.toString() ?: "",
                            it.child("lastName").value?.toString() ?: "",
                            it.child("contact").value?.toString() ?: "",
                            it.child("gender").value?.toString() ?: "",
                            it.child("qualification").value?.toString() ?: "",
                            it.child("cources").value?.toString() ?: "",
                        )
                        if (teacher != null) {
                            teacher.email = updatedValue
                            dr.child(newEmailKey).setValue(teacher).addOnSuccessListener {
                                dr.child(safeOldEmailKey).removeValue()
                                emailtxt.text = updatedValue
                                emailHeading.text = updatedValue
                                sp.putString("email", updatedValue)
                                sp.apply()
                                Toast.makeText(
                                    this,
                                    "Email updated successfully!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                dialog.dismiss()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Original data not found!", Toast.LENGTH_SHORT).show()
                    }
                }
                updates.clear()
            } else if (field == "Name") {
                val fullName = updatedValue
                val parts = fullName.split(" ")

                val firstName = parts[0]
                val lastName = parts[1]

                updates["firstName"] = firstName;
                updates["lastName"] = lastName;

                val safeKey = getSafeEmailKey(oldEmailKey)
                dr.child(safeKey).updateChildren(updates)
                    .addOnSuccessListener {
                        nametxt.text = updatedValue
                        nameHeading.text = updatedValue
                        sp.putString("name", updatedValue)
                        sp.apply()
                    }.addOnFailureListener {
                        Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
                    }
                dialog.dismiss()
                updates.clear()
            } else {
                updates[field.lowercase()] = updatedValue
                val safeKey = getSafeEmailKey(oldEmailKey)
                dr.child(safeKey).updateChildren(updates)
                    .addOnSuccessListener {
                        when (field) {
                            "Contact" -> {
                                contacttxt.text = updatedValue
                                sp.putString("contact", updatedValue)
                            }

                            "Gender" -> {
                                gendertxt.text = updatedValue
                                sp.putString("gender", updatedValue)
                            }

                            "Qualification" -> {
                                qualTxt.text = updatedValue
                                sp.putString("qualification", updatedValue)
                            }
                        }
                        sp.apply()
                        Toast.makeText(this, "$field updated successfully!", Toast.LENGTH_SHORT)
                            .show()
                        dialog.dismiss()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Update failed: ${it.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                updates.clear()
            }
        }

        val passBtn:Button = findViewById(R.id.passBtn);
        passBtn.setOnClickListener{
            val intent = Intent(this,changePass::class.java)
            intent.putExtra("path",path)
            startActivity(intent)
        }
    }

    private fun loadurl() {

        webView1 = findViewById(R.id.webView1)
        webView2 = findViewById(R.id.webView2)

//        setupWebView(webView1, "https://temp2.priyatal.buzz/upload.php")

//        setupWebView(webView1, "http://kevil.infinityfreeapp.com/upload.php") // remember

        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val subjects = sharedPreferences.getString("teacher_subject_map", null)
        val teacher = sharedPreferences.getString("name",null)

        if (subjects != null) {
            val encodedSubjects = URLEncoder.encode(subjects, "UTF-8")
//            val url = "http://kevil.infinityfreeapp.com/teacher2.php?subjects=$encodedSubjects"     //  remember
//            val url = "http://kevil.infinityfreeapp.com/teacher4.php?subjects=$encodedSubjects"
//            val url = "http://kevil.infinityfreeapp.com/teacher5.php?subjects=$encodedSubjects"     //  remember

            val url = "http://kevil.infinityfreeapp.com/teacher6.php?subjects=$encodedSubjects&teacher=$teacher"

//            val url2 = "http://kevil.infinityfreeapp.com/grade_assignment.php?subjects=$encodedSubjects"
//            val url2 = "http://kevil.infinityfreeapp.com/grade_assignment2.php?subjects=$encodedSubjects"
//            val url2 = "http://kevil.infinityfreeapp.com/grade_assignment3.php?subjects=$encodedSubjects"       //      remember
//            val url2 = "http://kevil.infinityfreeapp.com/grade_assignment4.php?subjects=$encodedSubjects"
//            val url2 = "http://kevil.infinityfreeapp.com/grade_assignment5.php?subjects=$encodedSubjects"
//            val url2 = "http://kevil.infinityfreeapp.com/grade_assignment6.php?subjects=$encodedSubjects"

            val jsonString = semesterJson.toString()

            val encodedJson = URLEncoder.encode(jsonString, "UTF-8")

//            val url2 = "http://kevil.infinityfreeapp.com/grade_assignment7.php?subjects=$encodedSubjects&student_data=$encodedJson"
//            val url2 = "http://kevil.infinityfreeapp.com/grade_assignment8.php?subjects=$encodedSubjects&student_data=$encodedJson"
            val url2 = "http://kevil.infinityfreeapp.com/grade_assignment5.php?subjects=$encodedSubjects&student_data=$encodedJson"

//            setupWebView(webView1, url)
            setupWebView(webView1, url)
            setupWebView(webView2, url2)
//            Toast.makeText(this, "Subjects: $subjects", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No subjects found in SharedPreferences", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun getSafeEmailKey(email: String): String {
        return email.replace("@", "_").replace(".", "-")
    }

    // Setup WebView with PDF and upload support
    private fun setupWebView(webView: WebView, url: String) {
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            allowFileAccess = true
            allowContentAccess = true
            loadWithOverviewMode = true
            useWideViewPort = true
        }

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url.toString()
                if (url.endsWith(".pdf")) {
                    openPdf(url)
                    return true
                }
                return false
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                this@teacher_main.filePathCallback = filePathCallback
                openFilePicker()
                return true
            }
        }
        webView.loadUrl(url)
    }

    // Open PDF in external viewer or Google Docs
    private fun openPdf(pdfUrl: String) {
        try {
            val googleDocsUrl = "https://docs.google.com/gview?embedded=true&url=$pdfUrl"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(googleDocsUrl))
            startActivity(intent)
        } catch (e: Exception) {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(Uri.parse(pdfUrl), "application/pdf")
                intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "No PDF viewer available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Open file picker for PDF upload
    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "application/pdf"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        startActivityForResult(intent, FILE_REQUEST_CODE)
    }

    // Handle the PDF upload result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE_REQUEST_CODE && resultCode == RESULT_OK) {
            val uri = data?.data
            filePathCallback?.onReceiveValue(arrayOf(uri!!))
        } else {
            filePathCallback?.onReceiveValue(null)
        }
        filePathCallback = null
    }

    // Request storage permissions
    private fun requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                101
            )
        }
    }

    // Handle back navigation for WebViews
    override fun onBackPressed() {
        when {
            findViewById<LinearLayout>(R.id.section1).visibility == View.VISIBLE && webView1.canGoBack() -> webView1.goBack()
            findViewById<LinearLayout>(R.id.section2).visibility == View.VISIBLE && webView2.canGoBack() -> webView2.goBack()
            else -> super.onBackPressed()
        }
    }
}

