package id.my.fanslab.movieapps.data.service.response

import com.google.gson.annotations.SerializedName
import id.my.fanslab.movieapps.data.model.TvShow

data class TvShowResponse(
    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<TvShow?>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)