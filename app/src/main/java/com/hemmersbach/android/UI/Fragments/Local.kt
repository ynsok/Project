package com.hemmersbach.android.UI.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hemmersbach.android.R
import com.hemmersbach.android.UI.RecyclerAdapter.RemoteRecycler
import com.hemmersbach.android.UI.RecyclerAdapter.TypeIcon
import com.hemmersbach.android.ViewModel.LocalViewModel
import com.hemmersbach.android.ViewModel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_local.view.*
import javax.inject.Inject

class Local : Fragment() {

    private lateinit var localViewModel: LocalViewModel
    private val mAdapter = RemoteRecycler(TypeIcon.LOCAL)
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_local, container, false)
        localViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(LocalViewModel::class.java)
        Log.i("AppfLocal", viewModelFactory.toString())
        Log.i("AppfLocal", localViewModel.toString())
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
