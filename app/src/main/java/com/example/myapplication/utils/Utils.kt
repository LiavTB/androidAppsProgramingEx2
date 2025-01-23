package com.example.myapplication.utils

import android.widget.ImageView
import com.example.myapplication.R

object Utils {

    fun setImageViewProfilePicture(pictureImageView: ImageView) {
        // Load the picture from the constant image resource
        pictureImageView.setImageResource(R.drawable.img)
    }

}