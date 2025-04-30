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
import com.example.myapplication.TeacherData
import com.google.firebase.database.FirebaseDatabase

class TeacherAdapter(private val teacherList: MutableList<TeacherData>) :
    RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    class TeacherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val deleteBtn: ImageView = itemView.findViewById(R.id.deleteBtn)
        val mainLayout: LinearLayout = itemView.findViewById(R.id.mainLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items, parent, false)
        return TeacherViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teacherList[position]
        holder.name.text = teacher.firstName + " " + teacher.lastName

        holder.deleteBtn.setOnClickListener {
            val databaseRef = FirebaseDatabase.getInstance().getReference("Register_info/Teacher")
            databaseRef.child(teacher.email.toString()).removeValue().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    teacherList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, teacherList.size)
                } else {
                    Toast.makeText(holder.itemView.context, "Failed to delete", Toast.LENGTH_SHORT).show()
                }
            }
        }

        holder.mainLayout.setOnClickListener {

            showCustomDialog(holder.itemView.context, teacher)
        }
    }

    override fun getItemCount(): Int = teacherList.size

    private fun showCustomDialog(context: Context, teacher: TeacherData) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.teacherdialog)


        val nameText: TextView = dialog.findViewById<TextView>(R.id.nametxt)
        val emailText: TextView = dialog.findViewById<TextView>(R.id.emailtxt)
        val genderText: TextView = dialog.findViewById<TextView>(R.id.gendertxt)
        val contactText: TextView = dialog.findViewById<TextView>(R.id.contacttxt)
        val qualiText : TextView = dialog.findViewById<TextView>(R.id.qualificationtxt)
        val courceText : TextView = dialog.findViewById<TextView>(R.id.coursetxt)

        nameText.text = teacher.firstName + " " + teacher.lastName
        emailText.text = getSafeEmailKey(teacher.email.toString())
        genderText.text = teacher.gender
        contactText.text = teacher.contact
        qualiText.text = teacher.qualification
        courceText.text = teacher.cources

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