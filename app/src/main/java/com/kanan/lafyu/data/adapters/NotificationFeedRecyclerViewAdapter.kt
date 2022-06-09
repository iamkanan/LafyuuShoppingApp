package com.kanan.lafyu.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kanan.lafyu.data.models.notificationResponse.NotificationFeedResponseModel
import com.kanan.lafyu.databinding.NotificationFeedListItemBinding

class NotificationFeedRecyclerViewAdapter :
    RecyclerView.Adapter<NotificationFeedRecyclerViewAdapter.NotificationFeedVH>() {

    private val list: MutableList<NotificationFeedResponseModel> = mutableListOf()

    fun setList(newList: List<NotificationFeedResponseModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class NotificationFeedVH(private val binding: NotificationFeedListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: NotificationFeedResponseModel) {
            binding.apply {
                notificationFeedImage.load(model.image) {
                    crossfade(true)
                }
                notificationFeedTitle.text = model.title
                notificationFeedDesc.text = model.description
                notificationFeedDate.text = model.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationFeedVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NotificationFeedListItemBinding.inflate(inflater, parent, false)
        return NotificationFeedVH(binding)
    }

    override fun onBindViewHolder(holder: NotificationFeedVH, position: Int) {
            list.getOrNull(position)?.let {
                holder.bind(it)
            }
    }

    override fun getItemCount(): Int = list.size
}