package com.kanan.lafyu.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kanan.lafyu.data.models.homeResponse.Category
import com.kanan.lafyu.databinding.CategoryListItemBinding


class CategoryRecyclerViewAdapter: RecyclerView.Adapter<CategoryRecyclerViewAdapter.VideoVH>() {

    private val list : MutableList<Category> = mutableListOf()

    fun setList(newList: List<Category>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class VideoVH(private val binding: CategoryListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Category) {
            binding.apply {
                circleImage.load(model.icon) {
                    crossfade(true)
                }
                listItemTitle.text = model.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryListItemBinding.inflate(inflater,parent,false)
        return VideoVH(binding)
    }

    override fun onBindViewHolder(holder: VideoVH, position: Int) {
        list.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = list.size


}