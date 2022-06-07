package id.my.fanslab.movieapps.data.service

import id.my.fanslab.movieapps.data.model.MovieDetail
import id.my.fanslab.movieapps.data.model.TvShowDetail
import id.my.fanslab.movieapps.data.service.response.MovieResponse
import id.my.fanslab.movieapps.data.service.response.ReviewResponse
import id.my.fanslab.movieapps.data.service.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    fun getMovie(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("movie/popular")
    fun getMovieTrending(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("movie/upcoming")
    fun getMovieDiscover(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id")movieId: Int, @Query("api_key") apiKey: String): Call<MovieDetail>

    @GET("movie/{movie_id}/reviews")
    fun getMovieReview(@Path("movie_id")movieId: Int, @Query("api_key") apiKey: String): Call<ReviewResponse>

    @GET("tv/on_the_air")
    fun getTvShow(@Query("api_key") apiKey: String): Call<TvShowResponse>

    @GET("tv/popular")
    fun getTvShowPopular(@Query("api_key") apiKey: String): Call<TvShowResponse>

    @GET("tv/top_rated")
    fun getTvShowDiscover(@Query("api_key") apiKey: String): Call<TvShowResponse>

    @GET("tv/{tv_id}")
    fun getTvDetail(@Path("tv_id") tvId: Int, @Query("api_key") apiKey: String): Call<TvShowDetail>

    @GET("tv/{tv_id}/reviews")
    fun getTvReview(@Path("tv_id") tvId: Int, @Query("api_key") apiKey: String): Call<ReviewResponse>

}