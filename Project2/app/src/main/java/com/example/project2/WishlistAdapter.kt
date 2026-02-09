package com.example.project2;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private val listItems: MutableList<WishList>, private val longPress: (Int) -> Unit) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val items = listItems.get(position)
        // Set item views based on views and data model
        holder.itemTextView.text = items.itmName
        holder.priceTextView.text = String.format("$%.2f", items.itmPrice)
        holder.urlTextView.text = items.itmURL

        holder.itemTextView.setOnLongClickListener {
            longPress(holder.bindingAdapterPosition)
            true
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Your holder should contain a member variable for any view that will be set as you render
        // a row
        val itemTextView: TextView
        val priceTextView: TextView
        val urlTextView: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            itemTextView = itemView.findViewById(R.id.itemTV)
            priceTextView = itemView.findViewById(R.id.priceTV)
            urlTextView = itemView.findViewById(R.id.urlTV)
        }
    }
}