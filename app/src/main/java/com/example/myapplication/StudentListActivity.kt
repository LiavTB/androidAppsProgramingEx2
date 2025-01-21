package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.StudentAdapter
import com.example.myapplication.repositories.StudentRepository

class StudentListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        // Set the toolbar title
        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
        toolbarTitle.text = getString(R.string.student_list_page_title)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(StudentRepository.getAllStudents()) { student ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("student_id", student.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
}