package com.example.myapplication.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.models.Student
import com.example.myapplication.repositories.StudentRepository

class NewStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        // Set the toolbar title
        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
        toolbarTitle.text = getString(R.string.new_student_page_title)

        // Set default image
        val pictureImageView: ImageView = findViewById(R.id.studentPicture)
        pictureImageView.setImageResource(R.drawable.img)

        val addButton: Button = findViewById(R.id.save_button)
        addButton.setOnClickListener {
            val id = findViewById<EditText>(R.id.studentId).text.toString()
            val name = findViewById<EditText>(R.id.studentName).text.toString()
            val phone = findViewById<EditText>(R.id.studentPhone).text.toString()
            val address = findViewById<EditText>(R.id.studentAddress).text.toString()
            val student = Student(id, name, phone, address)
            StudentRepository.addStudent(student)
            Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
        val cancelButton: Button = findViewById(R.id.cancel_button)
        cancelButton.setOnClickListener {
            finish()
        }

        val returnButton: ImageButton = findViewById(R.id.return_button)
        returnButton.setOnClickListener {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}