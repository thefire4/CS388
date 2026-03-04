package com.example.project5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BitFitAdapter(
    private val context: Context,
    private val entries: List<BitFitEntry>
) : RecyclerView.Adapter<BitFitAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_sleep_entry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(entries[position])
    }

    override fun getItemCount() = entries.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTV = itemView.findViewById<TextView>(R.id.dateTextView)
        private val hoursTV = itemView.findViewById<TextView>(R.id.hoursTextView)
        private val notesTV = itemView.findViewById<TextView>(R.id.notesTextView)

        fun bind(entry: BitFitEntry) {
            dateTV.text = entry.date
            hoursTV.text = "${entry.hoursSlept} hrs"
            notesTV.text = entry.notes ?: ""
        }
    }
}