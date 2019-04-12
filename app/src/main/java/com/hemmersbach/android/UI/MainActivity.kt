package com.hemmersbach.android.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.hemmersbach.android.R
import kotlinx.android.synthetic.main.activity_main.*
import com.hemmersbach.android.UI.ViewPagerAdapter.PagerAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
    }

    private fun initializeView() {
        viewpager.adapter = PagerAdapter(supportFragmentManager)
        tablayout.setupWithViewPager(viewpager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.settings -> {
                startActivity(SettingsActivity.initializeSettings(this))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
