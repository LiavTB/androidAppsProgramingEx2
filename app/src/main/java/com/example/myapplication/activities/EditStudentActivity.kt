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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.repositories.StudentRepository
import com.example.myapplication.utils.Utils.setImageViewProfilePicture

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        // Set the toolbar title
        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
        toolbarTitle.text = getString(R.string.edit_student_page_title)

        val studentId = intent.getStringExtra("student_id")
        val student = studentId?.let { StudentRepository.getStudentById(it) }

        val idEditText = findViewById<EditText>(R.id.studentId)
        val nameEditText = findViewById<EditText>(R.id.studentName)
        val phoneEditText = findViewById<EditText>(R.id.studentPhone)
        val addressEditText = findViewById<EditText>(R.id.studentAddress)
        val checkBox = findViewById<CheckBox>(R.id.studentCheckBox)
        val pictureImageView: ImageView = findViewById(R.id.studentPicture)

        idEditText.setText(student?.id)
        nameEditText.setText(student?.name)
        phoneEditText.setText(student?.phone)
        addressEditText.setText(student?.address)
        checkBox.isChecked = student?.isChecked ?: false

        // Load the picture from the constant image resource
        setImageViewProfilePicture(pictureImageView)

        findViewById<Button>(R.id.save_button).setOnClickListener {
            student?.id = idEditText.text.toString()
            student?.name = nameEditText.text.toString()
            student?.phone = phoneEditText.text.toString()
            student?.address = addressEditText.text.toString()
            student?.isChecked = checkBox.isChecked
            student?.let {
                StudentRepository.updateStudent(it)
                Toast.makeText(this, "Student saved", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this, StudentDetailsActivity::class.java).apply {
                putExtra("student_id", student?.id)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.delete_button).setOnClickListener {
            student?.let {
                StudentRepository.deleteStudent(it)
                Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, StudentListActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.cancel_button).setOnClickListener {
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
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}