package com.kanan.lafyu.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kanan.lafyu.data.models.notificationResponse.NotificationResponseModel
import com.kanan.lafyu.databinding.NotificationOfferListItemBinding

class NotificationOfferRecyclerViewAdapter: RecyclerView.Adapter<NotificationOfferRecyclerViewAdapter.NotificationOfferVH>() {

    private val list: MutableList<NotificationResponseModel> = mutableListOf()

    fun setList(newList: List<NotificationResponseModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class NotificationOfferVH(private val binding: NotificationOfferListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: NotificationResponseModel) {
            binding.apply {
                notificationOfferTitle.text = model.title
                notificationOfferDesc.text = model.description
                notificationOfferDate.text = model.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationOfferVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NotificationOfferListItemBinding.inflate(inflater,parent,false)
        return NotificationOfferVH(binding)
    }

    override fun onBindViewHolder(holder: NotificationOfferVH, position: Int) {
        list.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = list.size
}