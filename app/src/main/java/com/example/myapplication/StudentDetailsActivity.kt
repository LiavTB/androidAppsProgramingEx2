package com.example.myapplication

import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.repositories.StudentRepository

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        // Set the toolbar title
        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
        toolbarTitle.text = getString(R.string.student_details_page_title)

        val studentId = intent.getStringExtra("student_id")
        val student = StudentRepository.getStudentById(studentId)

        findViewById<TextView>(R.id.studentId).text = student?.id
        findViewById<TextView>(R.id.studentName).text = student?.name
        findViewById<TextView>(R.id.studentPhone).text = student?.phone
        findViewById<TextView>(R.id.studentAddress).text = student?.address
        findViewById<CheckBox>(R.id.studentCheckBox).isChecked = student?.isChecked ?: false
    }
}