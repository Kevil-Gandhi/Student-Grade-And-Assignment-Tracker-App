package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class viewNotices : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: noticeAdapter
    private val noticeList = ArrayList<Notices>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_notices)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            recyclerView = findViewById(R.id.recyNotice)

            recyclerView.layoutManager = LinearLayoutManager(this)
            adapter = noticeAdapter(noticeList)
            recyclerView.adapter = adapter

            fetchNotices()

            insets
        }

    }

    private fun fetchNotices() {
        val database = FirebaseDatabase.getInstance()
        val noticeRef = database.getReference("Register_info/Notice")

        noticeRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                noticeList.clear()
                for (noticeSnap in snapshot.children) {
                    val notice = noticeSnap.getValue(Notices::class.java)
                    if (notice != null) {
                        noticeList.add(notice)
                    }
                }
                noticeList.reverse()
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@viewNotices, "Error loading notices", Toast.LENGTH_SHORT).show()
            }
        })
    }
}