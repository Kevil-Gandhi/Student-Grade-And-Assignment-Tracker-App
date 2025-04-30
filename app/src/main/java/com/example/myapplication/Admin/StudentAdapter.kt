package com.example.myapplication.Admin

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.StudentData
import com.google.firebase.database.FirebaseDatabase

class StudentAdapter (private val studentList: MutableList<StudentData>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val deleteBtn: ImageView = itemView.findViewById(R.id.deleteBtn)
        val mainLayout: LinearLayout = itemView.findViewById(R.id.mainLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.name.text = student.firstName + " " + student.lastName

        holder.deleteBtn.setOnClickListener {
            val databaseRef = FirebaseDatabase.getInstance().getReference("Register_info/Student")
            databaseRef.child(student.email.toString()).removeValue().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    studentList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, studentList.size)
                } else {
                    Toast.makeText(holder.itemView.context, "Failed to delete", Toast.LENGTH_SHORT).show()
                }
            }
        }

        holder.mainLayout.setOnClickListener {
            showCustomDialog(holder.itemView.context, student)
        }
    }

    override fun getItemCount(): Int = studentList.size

    private fun showCustomDialog(context: Context, student: StudentData) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.studentdialog)


        val nameText: TextView = dialog.findViewById(R.id.nametxt)
        val emailText: TextView = dialog.findViewById(R.id.emailtxt)
        val genderText: TextView = dialog.findViewById(R.id.gendertxt)
        val contactText: TextView = dialog.findViewById(R.id.contacttxt)
        val rollText: TextView = dialog.findViewById(R.id.rolltxt)
        val semText: TextView = dialog.findViewById(R.id.semtxt)


        nameText.text = student.firstName + " " + student.lastName
        emailText.text = getSafeEmailKey(student.email.toString())
        genderText.text = student.gender
        contactText.text = student.contact
        rollText.text = student.rollNo.toString()
        semText.text = student.semester.toString()


        val closeButton = dialog.findViewById<Button>(R.id.okayButton)
        closeButton?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
    }
    private fun getSafeEmailKey(email: String): String {
        return email.replace("_", "@").replace("-", ".")
    }
}