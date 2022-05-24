package com.kanan.lafyu.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.kanan.lafyu.data.models.response.Product
import com.kanan.lafyu.databinding.FlashsaleListItemBinding
class FlashSaleRecyclerViewAdapter: RecyclerView.Adapter<FlashSaleRecyclerViewAdapter.FlashVH>() {

    private val list : MutableList<Product> = mutableListOf()

    fun setList(newList: List<Product>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class FlashVH(private val flashBinding: FlashsaleListItemBinding): RecyclerView.ViewHolder(flashBinding.root) {
        fun bind(model: Product) {
            flashBinding.apply {
                saleImage.load(model.images?.get(0)) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(50f,50f,50f,50f))
                }
                flashSaleProductName.text = model.title
                flashSaleProductDiscountPrice.text = model.discountPrice.toString()
                flashSaleProductPrice.text = model.price.toString()
                flashSaleProductDiscountPercent.text = model.discountPercent.toString() + "% Off"

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashVH {
        val inflater = LayoutInflater.from(parent.context)
        val flashBinding = FlashsaleListItemBinding.inflate(inflater,parent,false)
        return FlashVH(flashBinding)
    }

    override fun onBindViewHolder(holder: FlashVH, position: Int) {
        list.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = list.size

}