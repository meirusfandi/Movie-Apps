package id.my.fanslab.movieapps.data.service.response

import com.google.gson.annotations.SerializedName
import id.my.fanslab.movieapps.data.model.Review

data class ReviewResponse(
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<Review?>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)
