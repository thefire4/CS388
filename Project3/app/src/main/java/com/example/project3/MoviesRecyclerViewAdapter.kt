package com.example.project3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MoviesRecyclerViewAdapter(
    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MoviesRecyclerViewAdapter.ParkViewHolder>() {

    // Inflate the item layout from XML
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.frag_movies, parent, false)
        return ParkViewHolder(view)
    }

    // ViewHolder class holds references to all UI elements inside the list item layout
    inner class ParkViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null

        val mMovieName: TextView = mView.findViewById(R.id.movie_name)
        val mMovieDescription: TextView = mView.findViewById(R.id.movie_description)
        val mMovieImage: ImageView = mView.findViewById(R.id.movie_image)

        override fun toString(): String {
            return mMovieName.toString() + " '" + mMovieDescription.text + "'"
        }
    }

    override fun onBindViewHolder(holder: ParkViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mMovieName.text = movie.name
        holder.mMovieDescription.text = movie.description

        val imageUrl = "https://image.tmdb.org/t/p/w500" + movie.images
        Glide.with(holder.mView)
            .load(imageUrl)
            .centerInside()
            .into(holder.mMovieImage)


        // Sets up click listener for this park item
        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                mListener?.onItemClick(movie)
            }
        }
    }

    // Tells the RecyclerView how many items to display
    override fun getItemCount(): Int {
        return movies.size
    }
}