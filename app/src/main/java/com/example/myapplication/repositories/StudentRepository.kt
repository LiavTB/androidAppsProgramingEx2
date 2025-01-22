package com.example.myapplication.repositories

import com.example.myapplication.models.Student

object StudentRepository {
    private val students = mutableListOf<Student>(Student("1", "John Doe", "1234567890", "123 Main St", false))

    fun getAllStudents(): List<Student> = students

    fun addStudent(student: Student) {
        val exists = students.any { it.id == student.id }
        if (!exists) {
            students.add(student)
        }
    }

    fun updateStudent(student: Student) {
        val index = students.indexOfFirst { it.id == student.id }
        if (index != -1) {
            students[index] = student
        }
    }

    fun deleteStudent(student: Student) {
        val exists = students.any { it.id == student.id }
        if (exists) {
            students.remove(student)
        }
    }

    fun getStudentById(id: String): Student? {
        return students.find { it.id == id }
    }
}