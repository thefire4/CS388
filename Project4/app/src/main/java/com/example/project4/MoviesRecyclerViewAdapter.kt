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

class MoviesRecyclerViewAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.frag_movies, parent, false)
        return MovieViewHolder(view)
    }

    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mMovieName: TextView = mView.findViewById(R.id.movie_name)
        val mMovieImage: ImageView = mView.findViewById(R.id.movie_image)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.mMovieName.text = movie.name

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500" + movie.images)
            .centerCrop()
            .transform(RoundedCorners(50))
            .into(holder.mMovieImage)

        holder.mView.setOnClickListener {
            val intent = Intent(holder.mView.context, DetailActivity::class.java).apply {
                putExtra("TITLE", movie.name)
                putExtra("DESCRIPTION", movie.description)
                putExtra("IMAGE", movie.images)
                putExtra("RATING", movie.rating ?: 0.0)
                putExtra("RELEASE_DATE", movie.releaseDate)
            }
            holder.mView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = movies.size
}