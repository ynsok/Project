package com.hemmersbach.android.UI

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.hemmersbach.android.R
import kotlinx.android.synthetic.main.activity_main.*
import com.hemmersbach.android.UI.ViewPagerAdapter.PagerAdapter
import com.hemmersbach.android.ViewModel.MainViewModel
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
        Log.i("AppfMainActivity", mainViewModel.toString())
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
