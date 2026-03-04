package com.example.project5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.project5.databinding.ActivityDetailBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class BitFitCreateEntryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener {
            val date = binding.dateEntry.text.toString()
            val hours = binding.hoursEntry.text.toString().toFloatOrNull() ?: 0f

            lifecycleScope.launch(IO) {
                (application as BitFitApplication).db
                    .entryDao()
                    .insert(BitFitEntity(
                        date = date,
                        hoursSlept = hours,
                        notes = null
                    ))
            }
            finish()
        }
    }
}