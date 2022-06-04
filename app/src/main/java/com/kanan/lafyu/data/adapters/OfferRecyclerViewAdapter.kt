package com.kanan.lafyu.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kanan.lafyu.data.models.offerResponse.Product
import com.kanan.lafyu.databinding.RecommendListItemBinding

class OfferRecyclerViewAdapter: RecyclerView.Adapter<OfferRecyclerViewAdapter.OfferVH>() {

    private var list : MutableList<Product> = mutableListOf()

    fun setList(newList: List<Product>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class OfferVH(private val binding: RecommendListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Product) {
            binding.apply {
                recommendImage.load(model.thumbnailImage) {
                    crossfade(true)
                }
                recommendProductName.text = model.title
                recommendProductDiscountPrice.text = model.discountPrice.toString()
                recommendProductPrice.text = model.price.toString()
                recommendProductDiscountPercent.text = model.price.toString()
                root.setOnClickListener {
                    clickListener?.invoke(model)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecommendListItemBinding.inflate(inflater,parent,false)
        return OfferVH(binding)
    }

    override fun onBindViewHolder(holder: OfferVH, position: Int) {
        list.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    private var clickListener: ((Product) -> Unit)? = null

    fun setClickListener(listener: ((Product) -> Unit)?){
        this.clickListener = listener
    }

    override fun getItemCount(): Int = list.size
}