package com.example.project4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MoviesFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_movie_list, container, false)

        val progressBar    = view.findViewById<ContentLoadingProgressBar>(R.id.progress)
        val moviesRecycler = view.findViewById<RecyclerView>(R.id.movies_list)
        val tvRecycler     = view.findViewById<RecyclerView>(R.id.tv_list)

        moviesRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        tvRecycler.layoutManager     = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        fetchTrendingMovies(progressBar, moviesRecycler)
        fetchTrendingTv(progressBar, tvRecycler)

        return view
    }

    private fun fetchTrendingMovies(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        client["https://api.themoviedb.org/3/trending/movie/week", params, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                progressBar.hide()
                val dataJSON = json.jsonObject.get("results") as JSONArray
                val gson = Gson()
                val type = object : TypeToken<List<Movie>>() {}.type
                val movies: List<Movie> = gson.fromJson(dataJSON.toString(), type)
                recyclerView.adapter = MoviesRecyclerViewAdapter(movies)
                Log.d("MoviesFrag", "Trending movies loaded: ${movies.size}")
            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, t: Throwable?) {
                progressBar.hide()
                Log.e("MoviesFrag", "Movies fetch failed: $errorResponse")
            }
        }]
    }

    private fun fetchTrendingTv(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        client["https://api.themoviedb.org/3/trending/tv/week", params, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                progressBar.hide()
                val dataJSON = json.jsonObject.get("results") as JSONArray
                val gson = Gson()
                val type = object : TypeToken<List<TvShow>>() {}.type
                val shows: List<TvShow> = gson.fromJson(dataJSON.toString(), type)
                recyclerView.adapter = TvShowRecyclerViewAdapter(shows)
                Log.d("MoviesFrag", "Trending TV loaded: ${shows.size}")
            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, t: Throwable?) {
                progressBar.hide()
                Log.e("MoviesFrag", "TV fetch failed: $errorResponse")
            }
        }]
    }
}