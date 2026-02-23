package com.example.project4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title       = intent.getStringExtra("TITLE")
        val description = intent.getStringExtra("DESCRIPTION")
        val imagePath   = intent.getStringExtra("IMAGE")
        val rating      = intent.getDoubleExtra("RATING", 0.0)
        val releaseDate = intent.getStringExtra("RELEASE_DATE")

        supportActionBar?.title = title

        findViewById<TextView>(R.id.detail_title).text = title
        findViewById<TextView>(R.id.detail_description).text = description
        findViewById<TextView>(R.id.detail_rating).text = "Rating: " + String.format("%.1f", rating) + " / 10"
        findViewById<TextView>(R.id.detail_release_date).text = "Release Date: " + releaseDate

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500$imagePath")
            .centerCrop()
            .into(findViewById(R.id.detail_image))
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}