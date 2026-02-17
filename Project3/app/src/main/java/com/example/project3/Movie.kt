package com.example.project3

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

    /*
    @SerializedName("poster_path")
    var images: List<Image>? = null

    // Convenience property to access the first image’s URL

    val imageUrl: String? get() = images?.firstOrNull()?.url

    class Image {
        @SerializedName("url")
        var url: String? = null
    }
*/
}