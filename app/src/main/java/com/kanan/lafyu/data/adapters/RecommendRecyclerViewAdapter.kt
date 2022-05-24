package com.kanan.lafyu.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.kanan.lafyu.data.models.response.Product
import com.kanan.lafyu.databinding.RecommendListItemBinding

class RecommendRecyclerViewAdapter: RecyclerView.Adapter<RecommendRecyclerViewAdapter.RecommendVH>() {

    private var list : MutableList<Product> = mutableListOf()

    fun setList(newList: List<Product>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class RecommendVH(private val recommendBinding: RecommendListItemBinding): RecyclerView.ViewHolder(recommendBinding.root) {
        fun bind(model: Product) {
            recommendBinding.apply {
                recommendImage.load(model.images?.get(1)) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(50f,50f,50f,50f))
                }
                recommendProductName.text = model.title
                recommendProductDiscountPrice.text = model.discountPrice.toString()
                recommendProductPrice.text = model.price.toString()
                recommendProductDiscountPercent.text = model.discountPercent.toString() + "% Off"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendVH {
        val inflater = LayoutInflater.from(parent.context)
        val recommendBinding = RecommendListItemBinding.inflate(inflater,parent,false)
        return RecommendVH(recommendBinding)
    }

    override fun onBindViewHolder(holder: RecommendVH, position: Int) {
        list.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = list.size
}