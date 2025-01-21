package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.models.Student
import com.example.myapplication.repositories.StudentRepository

class NewStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        val addButton: Button = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val id = findViewById<EditText>(R.id.studentId).text.toString()
            val name = findViewById<EditText>(R.id.studentName).text.toString()
            val phone = findViewById<EditText>(R.id.studentPhone).text.toString()
            val address = findViewById<EditText>(R.id.studentAddress).text.toString()
            val student = Student(id, name, phone, address)
            StudentRepository.addStudent(student)
            finish()
        }
    }
}