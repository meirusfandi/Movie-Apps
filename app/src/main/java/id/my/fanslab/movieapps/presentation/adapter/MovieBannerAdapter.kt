package id.my.fanslab.movieapps.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.my.fanslab.movieapps.R
import id.my.fanslab.movieapps.data.model.Movie
import id.my.fanslab.movieapps.databinding.ItemBannerBinding
import id.my.fanslab.movieapps.presentation.activity.DetailActivity

class MovieBannerAdapter(private val listMovie: List<Movie>)
    : RecyclerView.Adapter<MovieBannerAdapter.BannerViewHolder>() {

    class BannerViewHolder(private val binding: ItemBannerBinding)
        :RecyclerView.ViewHolder(binding.root){

        fun bind(movie: Movie) = with(binding) {
            val url: String = "https://image.tmdb.org/t/p/original/"+movie.backdropPath
            Glide.with(itemView.context)
                .load(url)
                .error(R.drawable.ic_profile)
                .into(imgBanner)

            tvTitle.text = movie.title

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
    ): BannerViewHolder {
        return BannerViewHolder(
            ItemBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int {
        return if (listMovie.size > 5) 5
        else listMovie.size
    }
}