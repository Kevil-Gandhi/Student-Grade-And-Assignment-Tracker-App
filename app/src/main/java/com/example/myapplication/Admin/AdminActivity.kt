package com.example.myapplication.Admin

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Notices
import com.example.myapplication.R
import com.example.myapplication.StudentData
import com.example.myapplication.TeacherData
import com.example.myapplication.noticeAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AdminActivity : AppCompatActivity() {

    private lateinit var recyTeacher: RecyclerView
    private lateinit var recyStudent: RecyclerView
    private lateinit var recyNotice: RecyclerView
    private lateinit var teacherAdp: TeacherAdapter
    private lateinit var studentAdp: StudentAdapter
    private lateinit var noticeAdp: noticeAdapter

    private val teacherList = ArrayList<TeacherData>()
    private val studentList = ArrayList<StudentData>()
    private val noticelist = ArrayList<Notices>()

    private lateinit var teacherLayout: LinearLayout
    private lateinit var studentLayout: LinearLayout

    private lateinit var semesterSpinner: Spinner
    private val allStudents = ArrayList<StudentData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        teacherLayout = findViewById<LinearLayout>(R.id.teacherLayout)
        studentLayout = findViewById<LinearLayout>(R.id.studentLayout)

        recyTeacher = findViewById(R.id.recyTeacher)
        recyTeacher.layoutManager = LinearLayoutManager(this)

        recyStudent = findViewById(R.id.recyStudent)
        recyStudent.layoutManager = LinearLayoutManager(this)

        recyNotice = findViewById(R.id.recyNotice)
        recyNotice.layoutManager = LinearLayoutManager(this)

        semesterSpinner = findViewById(R.id.semesterSpinner)

        val semesterList = arrayOf("All", "1", "2", "3", "4", "5", "6")

        val semesterAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, semesterList)
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        semesterSpinner.adapter = semesterAdapter

        semesterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedSemester = parent.getItemAtPosition(position).toString()
                filterStudentsBySemester(selectedSemester)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        val showStudent: LinearLayout = findViewById(R.id.showStudent)
        val showTeacher: LinearLayout = findViewById(R.id.showTeacher)
        val noticeButton: LinearLayout = findViewById(R.id.noticeButton)
        val showNotice: ScrollView = findViewById(R.id.noticeLayout)

        teacherAdp = TeacherAdapter(teacherList)
        recyTeacher.adapter = teacherAdp
        fetchTeacherData()

        showTeacher.setOnClickListener {
            studentLayout.visibility = View.GONE
            teacherLayout.visibility = View.VISIBLE
            showNotice.visibility = View.GONE

            showStudent.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))
            showTeacher.setBackgroundColor(ContextCompat.getColor(this, R.color.BlueGreen))
            noticeButton.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))
        }

        studentAdp = StudentAdapter(studentList)
        recyStudent.adapter = studentAdp
        fetchStudentData()
        showStudent.setOnClickListener {
            studentLayout.visibility = View.VISIBLE
            teacherLayout.visibility = View.GONE
            showNotice.visibility = View.GONE

            showStudent.setBackgroundColor(ContextCompat.getColor(this, R.color.BlueGreen))
            showTeacher.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))
            noticeButton.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))

        }


        noticeAdp = noticeAdapter(noticelist)
        recyNotice.adapter = noticeAdp
        fetchNotices()

        noticeButton.setOnClickListener {
            studentLayout.visibility = View.GONE
            teacherLayout.visibility = View.GONE
            showNotice.visibility = View.VISIBLE

            showStudent.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))
            showTeacher.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))
            noticeButton.setBackgroundColor(ContextCompat.getColor(this, R.color.BlueGreen))
        }

        val submitButton: Button = findViewById(R.id.submitButton)
        submitButton.setOnClickListener {
            storeNotice();
        }

    }

    private fun storeNotice() {
        val database = FirebaseDatabase.getInstance()
        val noticeRef = database.getReference("Register_info/Notice");
        val noticeText: TextInputEditText = findViewById(R.id.noticeEditText)
        val noticeTitleText: TextInputEditText = findViewById(R.id.noticeTitleEditText)


        val currentDate = Date()
        val formatter = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        val formattedDateTime = formatter.format(currentDate)

        val noticeId = noticeRef.push().key
        val notice =
            Notices(noticeTitleText.text.toString(), noticeText.text.toString(), formattedDateTime)

        if (noticeId != null) {
            noticeRef.child(noticeId).setValue(notice).addOnSuccessListener {
                noticeText.setText("")
                noticeTitleText.setText("")
                fetchNotices()
                Toast.makeText(this, "Notice Has Been published", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun fetchTeacherData() {
        val database = FirebaseDatabase.getInstance()
        val teacherRef = database.getReference("Register_info/Teacher")

        teacherRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                teacherList.clear()

                for (teacherSnap in snapshot.children) {
                    val teacher = teacherSnap.getValue(TeacherData::class.java)

                    if (teacher != null) {
                        teacherList.add(teacher)
                    }

                }
                teacherAdp.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AdminActivity, "Couldn't Find Data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchStudentData() {
        val database = FirebaseDatabase.getInstance()
        val studentRef = database.getReference("Register_info/Student")

        studentRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                allStudents.clear()

                for (studentSnap in snapshot.children) {
                    val student = studentSnap.getValue(StudentData::class.java)
                    if (student != null) {
                        allStudents.add(student)
                    }
                }
                filterStudentsBySemester(semesterSpinner.selectedItem.toString()) // initial filter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AdminActivity, "Couldn't Find Data", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun filterStudentsBySemester(selectedSemester: String) {
        studentList.clear()
        if (selectedSemester == "All") {
            studentList.addAll(allStudents)
        } else {
            // Extract the integer from "Sem 5"
            val semNumber = selectedSemester.replace("Sem ", "").trim().toIntOrNull()

            if (semNumber != null) {
                studentList.addAll(allStudents.filter {
                    it.semester == semNumber
                })
            }
        }
        studentAdp.notifyDataSetChanged()
    }


    private fun fetchNotices() {
        val database = FirebaseDatabase.getInstance()
        val noticeRef = database.getReference("Register_info/Notice")

        noticeRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                noticelist.clear()

                for (noticeSnap in snapshot.children) {

                    val notice = noticeSnap.getValue(Notices::class.java)
                    if (notice != null) {
                        noticelist.add(notice)
                    }
                }
                noticelist.reverse()
                noticeAdp.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AdminActivity, "Couldn't Find Data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}