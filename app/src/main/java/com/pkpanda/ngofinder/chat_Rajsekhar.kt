package com.example.chatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(){

    lateinit var rcview: RecyclerView
    lateinit var message: TextView
    lateinit var send: FloatingActionButton
    lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rcview = findViewById(R.id.rcview)
        message = findViewById(R.id.message)
        send = findViewById(R.id.send)
        button2=findViewById(R.id.button2)
    }
}