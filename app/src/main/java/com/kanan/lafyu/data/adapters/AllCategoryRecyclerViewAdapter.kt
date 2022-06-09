package com.kanan.lafyu.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kanan.lafyu.data.models.categoryResponse.CategoryResponseModel
import com.kanan.lafyu.databinding.AllCategoryListItemBinding
import com.kanan.lafyu.utils.Resource

class AllCategoryRecyclerViewAdapter: RecyclerView.Adapter<AllCategoryRecyclerViewAdapter.AllCategoryVH>() {

    private val list: MutableList<CategoryResponseModel> = mutableListOf()

    fun setList(newList: List<CategoryResponseModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class AllCategoryVH(private val binding: AllCategoryListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CategoryResponseModel) {
            binding.apply {
                allCategoryListItemPic.load(model.icon) {
                    crossfade(true)
                }
                allCategoryListItemTitle.text = model.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCategoryVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AllCategoryListItemBinding.inflate(inflater,parent,false)
        return AllCategoryVH(binding)
    }

    override fun onBindViewHolder(holder: AllCategoryVH, position: Int) {
        list.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = list.size
}