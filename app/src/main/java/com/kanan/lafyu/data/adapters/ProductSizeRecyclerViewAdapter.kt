package com.kanan.lafyu.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kanan.lafyu.databinding.ProductSizeListItemBinding


class ProductSizeRecyclerViewAdapter :
    RecyclerView.Adapter<ProductSizeRecyclerViewAdapter.SizeVH>() {

    private val list: MutableList<String> = mutableListOf()

    fun setList(newList: List<String>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class SizeVH(private val binding: ProductSizeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: String) {
            binding.apply {
                size.text = model
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductSizeListItemBinding.inflate(inflater, parent, false)
        return SizeVH(binding)
    }

    override fun onBindViewHolder(holder: SizeVH, position: Int) {
        list.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = list.size


}