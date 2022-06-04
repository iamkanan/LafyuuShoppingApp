package com.kanan.lafyu.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import coil.load
import com.kanan.lafyu.R

class DetailPageViewPagerAdapter: PagerAdapter() {

    private val list: MutableList<String> = mutableListOf()

    fun setList(newList: List<String>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getCount(): Int = list.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(container.context)
        val slideLayout: View = layoutInflater.inflate(R.layout.detail_page_slider_item,container,false)

        val img: AppCompatImageView = slideLayout.findViewById(R.id.detailPageSliderImage)


        img.load(list.getOrNull(position))
        container.addView(slideLayout)
        return slideLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}