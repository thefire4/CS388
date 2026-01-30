 package com.example.lab1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

 class MainActivity : AppCompatActivity() {

     private var count: Long = 0

     private val themes = intArrayOf(
         R.style.Theme_Lab1_Level0,
         R.style.Theme_Lab1_Level1,
         R.style.Theme_Lab1_Level2,
         R.style.Theme_Lab1_Level3
     )

     override fun onCreate(savedInstanceState: Bundle?) {

         val prefs = getSharedPreferences("prefs", MODE_PRIVATE)

         val level = prefs.getInt("level", 0)

         setTheme(themes[level % themes.size])

         super.onCreate(savedInstanceState)
         enableEdgeToEdge()
         setContentView(R.layout.activity_main)

         ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
             val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
             v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
             insets
         }

         val pawButton = findViewById<ImageButton>(R.id.btnPaw)
         val upgradeButton = findViewById<Button>(R.id.upgradebtn)
         val resetButton = findViewById<Button>(R.id.resetBtn)
         val countTextView = findViewById<TextView>(R.id.counter)
         val trackerTextView = findViewById<TextView>(R.id.Tracker)

         count = prefs.getLong("count", 0L)

         val increment = 1 + level
         val goal = 100L * (1L shl level)

         countTextView.text = count.toString()
         trackerTextView.text = "Level $level   Goal $goal  +$increment/tap"

         if (count >= goal) {
             upgradeButton.visibility = View.VISIBLE
         } else {
             upgradeButton.visibility = View.GONE
         }

         pawButton.setOnClickListener {

             count = count + increment

             prefs.edit()
                 .putLong("count", count)
                 .apply()

             countTextView.text = count.toString()

             if (count >= goal) {
                 upgradeButton.visibility = View.VISIBLE
             }
         }

         upgradeButton.setOnClickListener {

             count = 0L
             countTextView.text = "0"
             upgradeButton.visibility = View.GONE

             prefs.edit()
                 .putInt("level", level + 1)
                 .putLong("count", 0L)
                 .apply()

             recreate()
         }

         resetButton.setOnClickListener {

             count = 0L
             countTextView.text = "0"
             upgradeButton.visibility = View.GONE
             trackerTextView.text = "Level 0   Goal 100  +1/tap"

             prefs.edit()
                 .putInt("level", 0)
                 .putLong("count", 0L)
                 .apply()

             recreate()
         }
     }
 }