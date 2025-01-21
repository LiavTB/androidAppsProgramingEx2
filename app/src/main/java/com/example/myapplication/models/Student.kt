package com.example.myapplication.models

data class Student(
    var id: String,
    var name: String,
    var phone: String, // P hone number - string because the number starts with 0
    var address: String,
    var isChecked: Boolean = false
)