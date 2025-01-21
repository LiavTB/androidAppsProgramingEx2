package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Student

class StudentAdapter(
    private val students: List<Student>,
    private val onItemClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student, onItemClick)
    }

    override fun getItemCount(): Int = students.size

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.studentName)
        private val idTextView: TextView = itemView.findViewById(R.id.studentId)
        private val phoneTextView: TextView = itemView.findViewById(R.id.studentPhone)
        private val addressTextView: TextView = itemView.findViewById(R.id.studentAddress)
        private val checkBox: CheckBox = itemView.findViewById(R.id.studentCheckBox)

        fun bind(student: Student, onItemClick: (Student) -> Unit) {
            nameTextView.text = student.name
            idTextView.text = student.id
            phoneTextView.text = student.phone
            addressTextView.text = student.address
            checkBox.isChecked = student.isChecked
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
            }
            itemView.setOnClickListener { onItemClick(student) }
        }
    }
}