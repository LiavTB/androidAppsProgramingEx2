package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.repositories.StudentRepository

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        // Set the toolbar title
        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
        toolbarTitle.text = getString(R.string.edit_student_page_title)

        val studentId = intent.getStringExtra("student_id")
        val student = StudentRepository.getStudentById(studentId)

        val idEditText = findViewById<EditText>(R.id.studentId)
        val nameEditText = findViewById<EditText>(R.id.studentName)
        val phoneEditText = findViewById<EditText>(R.id.studentPhone)
        val addressEditText = findViewById<EditText>(R.id.studentAddress)
        val checkBox = findViewById<CheckBox>(R.id.studentCheckBox)

        idEditText.setText(student?.id)
        nameEditText.setText(student?.name)
        phoneEditText.setText(student?.phone)
        addressEditText.setText(student?.address)
        checkBox.isChecked = student?.isChecked ?: false

        findViewById<Button>(R.id.updateButton).setOnClickListener {
            student?.id = idEditText.text.toString()
            student?.name = nameEditText.text.toString()
            student?.phone = phoneEditText.text.toString()
            student?.address = addressEditText.text.toString()
            student?.isChecked = checkBox.isChecked
            student?.let { StudentRepository.updateStudent(it) }
            finish()
        }

        findViewById<Button>(R.id.deleteButton).setOnClickListener {
            student?.let { StudentRepository.deleteStudent(it) }
            finish()
        }
    }
}