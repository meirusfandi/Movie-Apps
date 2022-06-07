package id.my.fanslab.movieapps.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.my.fanslab.movieapps.R
import id.my.fanslab.movieapps.data.model.TvShow
import id.my.fanslab.movieapps.databinding.ItemMovieBinding
import id.my.fanslab.movieapps.presentation.activity.DetailActivity

class TvShowAdapter(private val listTv: List<TvShow>)
    : RecyclerView.Adapter<TvShowAdapter.TvViewHolder>() {

    class TvViewHolder(private val binding: ItemMovieBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvShow) = with(binding) {
            val url: String = "https://image.tmdb.org/t/p/original/"+tv.posterPath
            Glide.with(itemView.context)
                .load(url)
                .error(R.drawable.ic_profile)
                .into(imgMovie)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.ID, tv.id)
                intent.putExtra(DetailActivity.TYPE, "TV")
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvViewHolder {
        return TvViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(listTv[position])
    }

    override fun getItemCount(): Int = listTv.size
}