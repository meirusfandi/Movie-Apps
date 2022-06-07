package id.my.fanslab.movieapps.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import id.my.fanslab.movieapps.R
import id.my.fanslab.movieapps.data.model.MovieDetail
import id.my.fanslab.movieapps.data.model.Review
import id.my.fanslab.movieapps.data.model.TvShowDetail
import id.my.fanslab.movieapps.data.service.ApiConfig
import id.my.fanslab.movieapps.data.service.response.ReviewResponse
import id.my.fanslab.movieapps.databinding.ActivityDetailBinding
import id.my.fanslab.movieapps.presentation.adapter.ReviewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val id =  intent.getIntExtra(ID, 0)
        val type =  intent.getStringExtra(TYPE)
        if (type == "MOVIE") getData(id)
        else getTvShow(id)

        if (type != null) {
            getReview(id, type)
        }
    }

    private fun getData(id: Int) = with(binding) {
        showLoading(true)
        val client = ApiConfig.getService().getMovieDetail(id, "233b568bea8fb27357da8dd3df2fc37f")
        client.enqueue(object : Callback<MovieDetail> {
            override fun onResponse(
                call: Call<MovieDetail>,
                response: Response<MovieDetail>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) setupData(responseBody)
                } else {
                    Log.e("DetailActivity", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                showLoading(false)
                Log.e("DetailActivity", "onFailure: ${t.message}")
            }
        })
    }

    private fun getReview(id: Int, type: String) = with(binding) {
        var client = ApiConfig.getService().getMovieReview(id, "233b568bea8fb27357da8dd3df2fc37f")
        if (type == "TV") {
            client = ApiConfig.getService().getTvReview(id, "233b568bea8fb27357da8dd3df2fc37f")
        }
        client.enqueue(object : Callback<ReviewResponse> {
            override fun onResponse(
                call: Call<ReviewResponse>,
                response: Response<ReviewResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) setupReview(responseBody.results)
                } else {
                    Log.e("DetailActivity", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                showLoading(false)
                Log.e("DetailActivity", "onFailure: ${t.message}")
            }

        })
    }

    fun setupReview(reviews: List<Review?>?) = with(binding){
        this!!.rvReview.layoutManager = LinearLayoutManager(
            baseContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val listReview = ArrayList<Review>()
        for (review in reviews!!) {
            listReview.add(review!!)
        }
        val adapter = ReviewAdapter(listReview)
        rvReview.adapter = adapter
    }

    private fun getTvShow(id: Int) = with(binding) {
        showLoading(true)
        val client = ApiConfig.getService().getTvDetail(id, "233b568bea8fb27357da8dd3df2fc37f")
        client.enqueue(object : Callback<TvShowDetail> {
            override fun onResponse(
                call: Call<TvShowDetail>,
                response: Response<TvShowDetail>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) setupTvData(responseBody)
                } else {
                    Log.e("DetailActivity", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowDetail>, t: Throwable) {
                showLoading(false)
                Log.e("DetailActivity", "onFailure: ${t.message}")
            }

        })
    }

    private fun showLoading(isLoading: Boolean = false) = with(binding) {
        this!!.progressBar.isVisible = isLoading
        rvReview.isVisible = isLoading.not()
        tvReviewLabel.isVisible = isLoading.not()
        tvVoteAverage.isVisible = isLoading.not()
        tvOverviewLabel.isVisible = isLoading.not()
        tvOverviewContent.isVisible = isLoading.not()
        tvReleaseDateContent.isVisible = isLoading.not()
        tvReleaseDateLabel.isVisible = isLoading.not()
    }

    private fun setupData(movie: MovieDetail) = with(binding){
        this!!.tvOverviewContent.text = movie.overview
        this.tvVoteAverage.text = "Rate : ${movie.voteAverage}"
        tvReleaseDateContent.text = movie.releaseDate
        val url: String = "https://image.tmdb.org/t/p/original/"+movie.backdropPath
        Glide.with(this@DetailActivity)
            .load(url)
            .error(R.drawable.ic_profile)
            .into(imgBackgrop)
    }

    private fun setupTvData(tv: TvShowDetail) = with(binding){
        this!!.tvOverviewContent.text = tv.overview
        this.tvVoteAverage.text = "Rate : ${tv.voteAverage}"
        tvReleaseDateContent.text = tv.firstAirDate
        val url: String = "https://image.tmdb.org/t/p/original/"+tv.backdropPath
        Glide.with(this@DetailActivity)
            .load(url)
            .error(R.drawable.ic_profile)
            .into(imgBackgrop)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TYPE = "TYPE"
        const val ID = "ID"
    }
}