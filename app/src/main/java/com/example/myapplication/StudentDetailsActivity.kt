package com.example.myapplication

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.repositories.StudentRepository

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Set the toolbar title
        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
        toolbarTitle.text = getString(R.string.student_details_page_title)

        val studentId = intent.getStringExtra("student_id")
        val student = StudentRepository.getStudentById(studentId ?: "")

        findViewById<EditText>(R.id.studentId).apply {
            setText(student?.id)
            isEnabled = false
            isFocusable = false
        }
        findViewById<EditText>(R.id.studentName).apply {
            setText(student?.name)
            isEnabled = false
            isFocusable = false
        }
        findViewById<EditText>(R.id.studentPhone).apply {
            setText(student?.phone)
            isEnabled = false
            isFocusable = false
        }
        findViewById<EditText>(R.id.studentAddress).apply {
            setText(student?.address)
            isEnabled = false
            isFocusable = false
        }
        findViewById<CheckBox>(R.id.studentCheckBox).apply {
            isChecked = student?.isChecked ?: false
            setOnCheckedChangeListener { _, isChecked ->
                if(student != null) {
                    // Handle the checkbox state change
                    student.isChecked = isChecked
                    // Update the student in the repository
                    StudentRepository.updateStudent(student)
                }
            }
        }

        // Load the picture from the local file
        val pictureImageView: ImageView = findViewById(R.id.studentPicture)
        val bitmap = BitmapFactory.decodeFile(student?.pictureUrl)
        if (bitmap != null) {
            pictureImageView.setImageBitmap(bitmap)
        }

        // Handle edit buttons
        findViewById<Button>(R.id.edit_button).setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student_id", studentId)
            startActivity(intent)
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