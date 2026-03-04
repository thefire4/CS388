package com.example.project5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project5.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val entries = mutableListOf<BitFitEntry>()
    private lateinit var adapter: BitFitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // DELETE enableEdgeToEdge() if it's here
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = BitFitAdapter(this, entries)
        binding.sleepList.adapter = adapter
        binding.sleepList.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            (application as BitFitApplication).db
                .entryDao()
                .getALL()
                .collect { entityList ->
                    entries.clear()
                    entries.addAll(entityList.map {
                        BitFitEntry(it.id, it.date, it.hoursSlept, it.notes)
                    })
                    adapter.notifyDataSetChanged()
                }
        }

        binding.addNew.setOnClickListener {
            startActivity(Intent(this, BitFitCreateEntryActivity::class.java))
        }
    }
}