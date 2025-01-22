package com.example.myapplication

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.repositories.StudentRepository

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        // Set the toolbar title
        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
        toolbarTitle.text = getString(R.string.edit_student_page_title)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

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

        // Load the picture from the local file
        val bitmap = BitmapFactory.decodeFile(student?.pictureUrl)
        if (bitmap != null) {
            pictureImageView.setImageBitmap(bitmap)
        }

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
            finish()
        }

        findViewById<Button>(R.id.deleteButton).setOnClickListener {
            student?.let {
                StudentRepository.deleteStudent(it)
                Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show()
            }
            finish()
        }

        findViewById<Button>(R.id.cancel_button).setOnClickListener {
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