package com.kanan.lafyu.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.kanan.lafyu.data.models.reviewResponse.Review
import com.kanan.lafyu.databinding.ReviewListItemBinding

class CommentRecyclerViewAdapter: RecyclerView.Adapter<CommentRecyclerViewAdapter.CommentVH>() {

    private val list: MutableList<Review> = mutableListOf()

    fun setList(newList: List<Review>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }



    inner class CommentVH(private val commentBinding: ReviewListItemBinding): RecyclerView.ViewHolder(commentBinding.root) {
        fun bind(model: Review) {
            commentBinding.apply {
                reviewUserImage.load("https://images.pexels.com/photos/2379004/pexels-photo-2379004.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500") {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                reviewUserName.text = model.fullName
                productSpesification.text = model.comment
                reviewDate.text = model.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ReviewListItemBinding.inflate(inflater, parent, false)
        return CommentVH(binding)
    }

    override fun onBindViewHolder(holder: CommentVH, position: Int) {
        list.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = list.size


}