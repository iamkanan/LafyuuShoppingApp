package com.kanan.lafyu.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kanan.lafyu.data.models.reviewResponse.StarModel
import com.kanan.lafyu.databinding.StarListItemBinding


class StarRecyclerViewAdapter: RecyclerView.Adapter<StarRecyclerViewAdapter.StarVH>(){

    val list = mutableListOf<StarModel>()

    fun addNewItem(star: List<StarModel>) {
        list.addAll(star)
        notifyItemInserted(list.size - 1)
    }


    inner class StarVH(private var starBinding: StarListItemBinding): RecyclerView.ViewHolder(starBinding.root) {
        fun bind(star: StarModel) {
            starBinding.apply {
                num.text = star.num.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = StarListItemBinding.inflate(layoutInflater,parent,false)
        val starVH = StarVH(binding)
        return starVH
    }

    override fun onBindViewHolder(holder: StarVH, position: Int) {
        list.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = list.size

}