package com.example.myapplication.repositories

import com.example.myapplication.models.Student

object StudentRepository {
    private val students = mutableListOf<Student>()

    fun getAllStudents(): List<Student> = students

    fun addStudent(student: Student) {
        // TODO: check if student already exists
        students.add(student)
    }

    fun updateStudent(student: Student) {
        val index = students.indexOfFirst { it.id == student.id }
        if (index != -1) {
            students[index] = student
        }
    }

    fun deleteStudent(student: Student) {
        students.remove(student)
    }

    fun getStudentById(id: String): Student? {
        return students.find { it.id == id }
    }
}