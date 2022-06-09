package com.kanan.lafyu.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kanan.lafyu.data.models.notificationResponse.NotificationActivityResponseModel
import com.kanan.lafyu.databinding.NotificationActivityListItemBinding

class NotificationActivityRecyclerViewAdapter: RecyclerView.Adapter<NotificationActivityRecyclerViewAdapter.NotificationActivityVH>() {

    private val list: MutableList<NotificationActivityResponseModel> = mutableListOf()

    fun setList(newList: List<NotificationActivityResponseModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class NotificationActivityVH(private val binding: NotificationActivityListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: NotificationActivityResponseModel) {
            binding.apply {
                notificationActivityTitle.text = model.title
                notificationActivityDesc.text = model.description
                notificationActivityDate.text = model.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationActivityVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NotificationActivityListItemBinding.inflate(inflater,parent,false)
        return NotificationActivityVH(binding)
    }

    override fun onBindViewHolder(holder: NotificationActivityVH, position: Int) {
        list.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = list.size
}