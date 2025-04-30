package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class noticeAdapter(private val noticeList: ArrayList<Notices>) :
    RecyclerView.Adapter<noticeAdapter.noticeViewHolder>() {

    class noticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val showBtn: ImageView = itemView.findViewById(R.id.showBtn)
        val mainLayout: LinearLayout = itemView.findViewById(R.id.mainLayout)
        val noticeText:TextView = itemView.findViewById(R.id.noticetext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noticeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.noticeitems, parent, false)
        return noticeViewHolder(view)
    }

    override fun onBindViewHolder(holder: noticeViewHolder, position: Int) {
        val notice = noticeList[position]
        holder.title.setText(notice.title)
        val safeText = notice.text ?: ""
        val shortText = if (safeText.length > 20) safeText.substring(0, 20) + "..." else safeText
        holder.noticeText.setText(shortText)

        holder.showBtn.setOnClickListener {
            showCustomDialog(holder.itemView.context,notice)
        }

        holder.mainLayout.setOnClickListener{
            showCustomDialog(holder.itemView.context,notice)
        }
    }

    override fun getItemCount(): Int = noticeList.size

    private fun showCustomDialog(context: Context, notice: Notices) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.noticedialog)

        val title: TextView = dialog.findViewById(R.id.titletxt)
        val date: TextView = dialog.findViewById(R.id.datatxt)
        val noticetxt: TextView = dialog.findViewById(R.id.noticetxt)

        title.text = notice.title
        noticetxt.text = notice.text
        date.text = notice.timestamp

        val closeButton = dialog.findViewById<Button>(R.id.okayButton)
        closeButton?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
    }

}