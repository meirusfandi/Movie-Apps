package id.my.fanslab.movieapps.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.my.fanslab.movieapps.R
import id.my.fanslab.movieapps.data.model.TvShow
import id.my.fanslab.movieapps.databinding.ItemBannerBinding
import id.my.fanslab.movieapps.presentation.activity.DetailActivity

class TvShowBannerAdapter(private val listTv: List<TvShow>)
    : RecyclerView.Adapter<TvShowBannerAdapter.BannerViewHolder>() {

    class BannerViewHolder(private val binding: ItemBannerBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(tv: TvShow) = with(binding) {
            val url: String = "https://image.tmdb.org/t/p/original/"+tv.backdropPath
            Glide.with(itemView.context)
                .load(url)
                .error(R.drawable.ic_profile)
                .into(imgBanner)

            tvTitle.text = tv.name

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
        holder.bind(listTv[position])
    }

    override fun getItemCount(): Int {
        return if (listTv.size > 5) 5
        else listTv.size
    }
}