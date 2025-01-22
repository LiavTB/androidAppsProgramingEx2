package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.repositories.StudentRepository
import com.example.myapplication.utils.Utils.setImageViewProfilePicture

class StudentDetailsActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        // Refresh the data in the adapter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

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

        val pictureImageView: ImageView = findViewById(R.id.studentPicture)
        // Load the picture from the constant image resource
        setImageViewProfilePicture(pictureImageView)

        // Handle edit buttons
        findViewById<Button>(R.id.edit_button).setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student_id", studentId)
            startActivity(intent)
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