package id.my.fanslab.movieapps.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.my.fanslab.movieapps.R
import id.my.fanslab.movieapps.data.model.Movie
import id.my.fanslab.movieapps.databinding.ItemMovieBinding
import id.my.fanslab.movieapps.presentation.activity.DetailActivity

class MovieAdapter(private val listMovie: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: ItemMovieBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) = with(binding) {
            val url: String = "https://image.tmdb.org/t/p/original/"+movie.posterPath
            Glide.with(itemView.context)
                .load(url)
                .error(R.drawable.ic_profile)
                .into(imgMovie)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.ID, movie.id)
                intent.putExtra(DetailActivity.TYPE, "MOVIE")
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size
}