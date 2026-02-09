package com.example.project2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var itemName: EditText
    lateinit var itemURL: EditText
    lateinit var itemPrice: EditText
    lateinit var submitBtn : Button
    lateinit var listItems: MutableList<WishList>
    lateinit var adapter: WishlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val ime = insets.getInsets(WindowInsetsCompat.Type.ime())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, maxOf(systemBars.bottom, ime.bottom))
            insets
        }

        val wishlistRv = findViewById<RecyclerView>(R.id.wishRV)

        listItems = mutableListOf()
        adapter = WishlistAdapter(listItems) {position ->
                listItems.removeAt(position)
                adapter.notifyItemRemoved(position)
        }
        itemName = findViewById<EditText>(R.id.itemName)
        itemURL = findViewById<EditText>(R.id.itemURL)
        itemPrice = findViewById<EditText>(R.id.itemPrice)
        submitBtn = findViewById<Button>(R.id.submitBtn)

        wishlistRv.adapter = adapter
        wishlistRv.layoutManager = LinearLayoutManager(this)

        submitBtn.setOnClickListener {
            var name = itemName.text.toString().trim()
            var price = itemPrice.text.toString().toFloat()
            var url = itemURL.text.toString().trim()
            val item = WishList(name, price, url)
            listItems.add(item)

            itemName.text.clear()
            itemPrice.text.clear()
            itemURL.text.clear()
        }


    }
}