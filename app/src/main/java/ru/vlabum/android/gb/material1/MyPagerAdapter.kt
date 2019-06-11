package ru.vlabum.android.gb.material1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPagerAdapter(val fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    val fragmentList: MutableList<Fragment> = ArrayList()
    val titleList: MutableList<String> = ArrayList()

    override fun getItem(position: Int): Fragment? {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

    public fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }
}