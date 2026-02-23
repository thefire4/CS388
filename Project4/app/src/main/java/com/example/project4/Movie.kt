package com.example.project4

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single park from the National Parks API.
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class Movie {
    @SerializedName("title")
    var name: String? = null

    @SerializedName("overview")
    var description: String? = null

    @SerializedName("poster_path")
    var images: String? = null

    @SerializedName("vote_average")
    var rating: Double? = null

    @SerializedName("release_date")
    var releaseDate: String? = null
}