package com.porto.anycook.ui.commons

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(fragment: Fragment)
    : FragmentStateAdapter(fragment) {

    private val pages = arrayListOf<String>()

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun createFragment(position: Int): Fragment {
        return Class.forName(pages[position]).newInstance() as Fragment
    }

    fun addFragment(fragmentClassName: String) {
        pages.add(fragmentClassName)
    }
}