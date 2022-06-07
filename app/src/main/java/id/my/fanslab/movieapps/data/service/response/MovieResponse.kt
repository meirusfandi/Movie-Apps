package id.my.fanslab.movieapps.data.service.response

import com.google.gson.annotations.SerializedName
import id.my.fanslab.movieapps.data.model.Dates
import id.my.fanslab.movieapps.data.model.Movie

data class MovieResponse(
    @field:SerializedName("dates")
    val dates: Dates? = null,

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<Movie?>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)