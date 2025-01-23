package com.example.myapplication.models

import com.example.myapplication.R

data class Student(
    var id: String,
    var name: String,
    var phone: String, // P hone number - string because the number starts with 0
    var address: String,
    var isChecked: Boolean = false,
    var pictureUrl: String = "none"
)