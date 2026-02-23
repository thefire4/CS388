package com.example.project4

import com.google.gson.annotations.SerializedName

class TvShow {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("overview")
    var description: String? = null

    @SerializedName("poster_path")
    var images: String? = null

    @SerializedName("vote_average")
    var rating: Double? = null

    @SerializedName("first_air_date")
    var releaseDate: String? = null
}