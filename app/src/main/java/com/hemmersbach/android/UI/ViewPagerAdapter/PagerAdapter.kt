package com.hemmersbach.android.UI.ViewPagerAdapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.hemmersbach.android.UI.Fragments.Local
import com.hemmersbach.android.UI.Fragments.Remote

class PagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> Remote()
            1 -> Local()
            else -> Remote()
        }
    }

    override fun getCount(): Int = NUM_OF_FRAGMENTS
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> Remote::class.java.simpleName
            1 -> Local::class.java.simpleName
            else -> Remote::class.java.simpleName
        }
    }

    companion object {
        private const val NUM_OF_FRAGMENTS = 2
    }
}