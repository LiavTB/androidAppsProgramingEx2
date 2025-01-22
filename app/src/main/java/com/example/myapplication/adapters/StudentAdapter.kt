package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Student
import com.example.myapplication.utils.Utils.setImageViewProfilePicture

class StudentAdapter(
    private var students: List<Student>,
    private val onItemClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.init(student, onItemClick)
    }

    fun updateData(newStudents: List<Student>) {
        students = newStudents
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = students.size

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.studentName)
        private val idTextView: TextView = itemView.findViewById(R.id.studentId)
        private val checkBox: CheckBox = itemView.findViewById(R.id.studentCheckBox)
        private val pictureImageView: ImageView = itemView.findViewById(R.id.studentPicture)

        fun init(student: Student, onItemClick: (Student) -> Unit) {
            nameTextView.text = student.name
            idTextView.text = student.id
            checkBox.isChecked = student.isChecked
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
            }
            // Load the picture from the constant image resource
            setImageViewProfilePicture(pictureImageView)

            itemView.setOnClickListener { onItemClick(student) }
        }
    }
}