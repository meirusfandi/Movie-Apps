package id.my.fanslab.movieapps.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @field:SerializedName("imdb_id")
    val imdbId: String,

    @field:SerializedName("video")
    val video: Boolean,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("revenue")
    val revenue: Int,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("vote_count")
    val voteCount: Int,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("tagline")
    val tagline: String,

    @field:SerializedName("homepage")
    val homepage: String,

    @field:SerializedName("status")
    val status: String
)
