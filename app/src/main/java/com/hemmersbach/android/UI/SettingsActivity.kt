package com.hemmersbach.android.UI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hemmersbach.android.UI.Fragments.SettingsFragment

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()
        supportFragmentManager.beginTransaction().replace(android.R.id.content, SettingsFragment())
            .commit()
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        fun initializeSettings(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }
}