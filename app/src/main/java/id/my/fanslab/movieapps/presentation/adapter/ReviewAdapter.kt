package id.my.fanslab.movieapps.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.my.fanslab.movieapps.R
import id.my.fanslab.movieapps.data.model.Review
import id.my.fanslab.movieapps.databinding.ItemReviewBinding

class ReviewAdapter(private val listReview: List<Review>)
    : RecyclerView.Adapter<ReviewAdapter.ReviewHolder>(){
    class ReviewHolder(private val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(review: Review) = with(binding) {
            tvReviewContent.text = review.content
            tvReviewerName.text = review.author
            Glide.with(itemView.context)
                .load(review.authorDetails?.avatarPath)
                .error(R.drawable.ic_profile)
                .into(imgReviewers)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        return ReviewHolder(
            ItemReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        holder.bind(listReview[position])
    }

    override fun getItemCount(): Int {
        return if (listReview.size > 5) 5
        else listReview.size
    }
}