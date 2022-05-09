package com.excelsior.codechallenge.infrastructure.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.excelsior.codechallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        setContentView(activityBinding.root)
    }
}