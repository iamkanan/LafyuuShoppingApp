import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Transformation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.kanan.lafyu.data.models.detailResponse.TopReview
import com.kanan.lafyu.databinding.ProductSizeListItemBinding
import com.kanan.lafyu.databinding.ReviewListItemBinding

class ReviewRecyclerViewAdapter :
    RecyclerView.Adapter<ReviewRecyclerViewAdapter.ReviewVH>() {

    private val list: MutableList<TopReview> = mutableListOf()

    fun setList(newList: TopReview) {
        list.clear()
        list.addAll(listOf(newList))
        notifyDataSetChanged()
    }

    inner class ReviewVH(private val binding: ReviewListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: TopReview) {
            binding.apply {
                reviewUserImage.load("https://static.photocdn.pt/images/articles/2019/08/07/images/articles/2019/07/31/best_linkedin_photos.webp") {
                    transformations(CircleCropTransformation())
                }
                reviewUserName.text = model.fullName
                productSpesification.text = model.comment
                reviewDate.text = model.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ReviewListItemBinding.inflate(inflater, parent, false)
        return ReviewVH(binding)
    }

    override fun onBindViewHolder(holder: ReviewVH, position: Int) {
        list.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = list.size


}