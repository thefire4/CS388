package com.example.project4

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop


class TvShowRecyclerViewAdapter(
    private val shows: List<TvShow>
) : RecyclerView.Adapter<TvShowRecyclerViewAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.frag_movies, parent, false)
        return TvShowViewHolder(view)
    }

    inner class TvShowViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mShowName: TextView = mView.findViewById(R.id.movie_name)
        val mShowImage: ImageView = mView.findViewById(R.id.movie_image)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val show = shows[position]

        holder.mShowName.text = show.name

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500" + show.images)
            .centerCrop()
            .transform(RoundedCorners(50))
            .into(holder.mShowImage)


        holder.mView.setOnClickListener {
            val intent = Intent(holder.mView.context, DetailActivity::class.java).apply {
                putExtra("TITLE", show.name)
                putExtra("DESCRIPTION", show.description)
                putExtra("IMAGE", show.images)
                putExtra("RATING", show.rating ?: 0.0)
                putExtra("RELEASE_DATE", show.releaseDate)
            }
            holder.mView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = shows.size
}