package com.example.cleanwater_project.presentation.rest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cleanwater_project.R
import com.example.data.repository.Repositories

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Repositories.init(applicationContext)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}