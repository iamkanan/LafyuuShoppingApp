package com.kanan.lafyu.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager.widget.PagerAdapter
import coil.load
import com.kanan.lafyu.R
import com.kanan.lafyu.data.models.response.Advertisment

class HomePageViewPagerAdapter(): PagerAdapter() {

    private val list : MutableList<Advertisment> = mutableListOf()

    fun setList(newList: List<Advertisment>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }


    override fun getCount(): Int = list.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(container.context)
        val slideLayout: View = layoutInflater.inflate(R.layout.main_page_slider_item,container,false)

        val img : AppCompatImageView = slideLayout.findViewById(R.id.homePageSliderImage)
        val title: AppCompatTextView = slideLayout.findViewById(R.id.homePageSliderTitle)
        val hours: AppCompatTextView = slideLayout.findViewById(R.id.homePageSliderTime1)
        val minutes: AppCompatTextView = slideLayout.findViewById(R.id.homePageSliderTime2)
        val secs: AppCompatTextView = slideLayout.findViewById(R.id.homePageSliderTime3)

        list.getOrNull(position)?.let {
            img.load(it.image)
            title.text = list[position].title
            hours.text = list[position].date?.substring(0,2)
            minutes.text = list[position].date?.substring(3,5)
            secs.text = list[position].date?.substring(6,8)
        }

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