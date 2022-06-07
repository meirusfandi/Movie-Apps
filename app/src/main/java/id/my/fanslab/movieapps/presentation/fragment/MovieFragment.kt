package id.my.fanslab.movieapps.presentation.fragment

import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import id.my.fanslab.movieapps.data.model.Movie
import id.my.fanslab.movieapps.data.service.ApiConfig
import id.my.fanslab.movieapps.data.service.response.MovieResponse
import id.my.fanslab.movieapps.databinding.FragmentMovieBinding
import id.my.fanslab.movieapps.presentation.adapter.MovieAdapter
import id.my.fanslab.movieapps.presentation.adapter.MovieBannerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvMovie?.apply {
            layoutManager = llManager
            setHasFixedSize(true)
        }
        handler.postDelayed(runnable, speedScroll.toLong())
        binding?.rvTrending?.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding?.rvDiscover?.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        getData()
        getDiscover()
        getTrending()
    }

    private val llManager: LinearLayoutManager = object : LinearLayoutManager(context , HORIZONTAL,false) {
        override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State, position: Int) {
            val smoothScroller: LinearSmoothScroller =
                object : LinearSmoothScroller(context) {
                    private val SPEED = 4000f
                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                        return SPEED
                    }
                }
            smoothScroller.targetPosition = position
            startSmoothScroll(smoothScroller)
        }
    }

    private fun getData() = with(binding) {
        showLoading(true)
        val client = ApiConfig.getService().getMovie("233b568bea8fb27357da8dd3df2fc37f")
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) setupData(responseBody.results as List<Movie>)
                } else {
                    Log.e("MainActivity", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                showLoading(false)
                Log.e("MainActivity", "onFailure: ${t.message}")
            }

        })
    }

    private fun getDiscover() = with(binding) {
        showLoading(true)
        val client = ApiConfig.getService().getMovieDiscover("233b568bea8fb27357da8dd3df2fc37f")
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) setupDiscover(responseBody.results as List<Movie>)
                } else {
                    Log.e("MainActivity", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                showLoading(false)
                Log.e("MainActivity", "onFailure: ${t.message}")
            }

        })

    }

    private fun getTrending() = with(binding) {
        showLoading(true)
        val client = ApiConfig.getService().getMovieTrending("233b568bea8fb27357da8dd3df2fc37f")
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) setupTrending(responseBody.results as List<Movie>)
                } else {
                    Log.e("MainActivity", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                showLoading(false)
                Log.e("MainActivity", "onFailure: ${t.message}")
            }

        })
    }

    private fun setupData(movies: List<Movie>) = with(binding){
        val listMovie = ArrayList<Movie>()
        for (movie in movies) {
            listMovie.add(movie)
        }
        val adapter = MovieBannerAdapter(listMovie)
        this?.rvMovie?.adapter = adapter
    }

    private fun setupDiscover(movies: List<Movie>) = with(binding){
        val listMovie = ArrayList<Movie>()
        for (movie in movies) {
            listMovie.add(movie)
        }
        val adapter = MovieAdapter(listMovie)
        this?.rvDiscover?.adapter = adapter
    }

    private fun setupTrending(movies: List<Movie>) = with(binding){
        val listMovie = ArrayList<Movie>()
        for (movie in movies) {
            listMovie.add(movie)
        }
        val adapter = MovieAdapter(listMovie)
        this?.rvTrending?.adapter = adapter
    }

    private val handler = Handler()
    private val speedScroll = 1
    private val runnable = object : Runnable {
        var count = 0
        override fun run() {
            if (count == binding?.rvMovie?.adapter?.itemCount) count = 0
            if (count < (binding?.rvMovie?.adapter?.itemCount ?: -1)) {
                binding?.rvMovie?.smoothScrollToPosition(++count)
                handler.postDelayed(this, speedScroll.toLong())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, speedScroll.toLong())
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        handler.removeCallbacks(runnable)
    }

    private fun showLoading(isLoading: Boolean) = with(binding){
        if (isLoading) {
            this!!.progressBar.visibility = View.VISIBLE
            tvTrending.visibility = View.GONE
            tvDiscover.visibility = View.GONE
            this.rvMovie.visibility = View.GONE
            this.rvTrending.visibility = View.GONE
            this.rvDiscover.visibility = View.GONE
        } else {
            this!!.progressBar.visibility = View.GONE
            tvTrending.visibility = View.VISIBLE
            tvDiscover.visibility = View.VISIBLE
            this.rvMovie.visibility = View.VISIBLE
            this.rvTrending.visibility = View.VISIBLE
            this.rvDiscover.visibility = View.VISIBLE
        }
    }
}
