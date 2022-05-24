package com.kanan.lafyu.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.kanan.lafyu.data.models.response.Product
import com.kanan.lafyu.databinding.MegasaleListItemBinding

class MegaSaleRecyclerViewAdapter : RecyclerView.Adapter<MegaSaleRecyclerViewAdapter.MegaVH>() {

    private val list: MutableList<Product> = mutableListOf()

    fun setList(newList: List<Product>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class MegaVH(private val megaBinding: MegasaleListItemBinding) :
        RecyclerView.ViewHolder(megaBinding.root) {
        fun bind(model: Product) {
            megaBinding.apply {
                megaImage.load(model.images?.get(1)) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(50f, 50f, 50f, 50f))
                }
                megaSaleProductName.text = model.title
                megaSaleProductDiscountPrice.text = model.discountPrice.toString()
                megaSaleProductPrice.text = model.price.toString()
                megaSaleProductDiscountPercent.text = model.discountPercent.toString() + "% Off"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MegaVH {
        val inflater = LayoutInflater.from(parent.context)
        val megaBinding = MegasaleListItemBinding.inflate(inflater, parent, false)
        return MegaVH(megaBinding)
    }

    override fun onBindViewHolder(holder: MegaVH, position: Int) {
        list.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = list.size

}