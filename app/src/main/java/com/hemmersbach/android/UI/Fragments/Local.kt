package com.hemmersbach.android.UI.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hemmersbach.android.R
import com.hemmersbach.android.UI.RecyclerAdapter.RemoteRecycler
import com.hemmersbach.android.UI.RecyclerAdapter.TypeIcon
import com.hemmersbach.android.ViewModel.LocalViewModel
import com.hemmersbach.android.ViewModel.LocalViewModelFactory
import kotlinx.android.synthetic.main.fragment_local.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.kodein
import org.kodein.di.generic.instance

class Local : Fragment(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val localViewModelFactory: LocalViewModelFactory by instance()
    private lateinit var localViewModel: LocalViewModel
    private val mAdapter = RemoteRecycler(TypeIcon.LOCAL)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_local, container, false)
        localViewModel =
            ViewModelProviders.of(this, localViewModelFactory).get(LocalViewModel::class.java)
        initializeView(view)
        getLocalJokes()
        removeFromDatabase()
        return view
    }

    private fun initializeView(view: View) {
        view.local_recyclerView.adapter = mAdapter
    }

    private fun getLocalJokes() {
        localViewModel.getAllJokes().observe(this, Observer { mAdapter.swapData(it!!) })
    }

    private fun removeFromDatabase() {
        mAdapter.removeFromDatabase = { newValue, oldValue ->
            localViewModel.removeFromDatabase(newValue)
            mAdapter.removeJoke(oldValue)
        }
    }
}
