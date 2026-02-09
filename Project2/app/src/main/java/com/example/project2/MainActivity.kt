package com.example.project2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var itemName: EditText
    lateinit var itemURL: EditText
    lateinit var itemPrice: EditText
    lateinit var submitBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        itemName = findViewById<EditText>(R.id.itemName)
        itemURL = findViewById<EditText>(R.id.itemURL)
        itemPrice = findViewById<EditText>(R.id.itemPrice)
        submitBtn = findViewById<Button>(R.id.submitBtn)



        submitBtn.setOnClickListener {
            var name = itemName.text.toString().trim()
            var price = itemPrice.text
            var url = itemPrice.text.toString().trim()

        }
    }
}