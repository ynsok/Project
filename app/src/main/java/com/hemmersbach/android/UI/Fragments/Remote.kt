package com.hemmersbach.android.UI.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hemmersbach.android.R
import com.hemmersbach.android.UI.RecyclerAdapter.RemoteRecycler
import com.hemmersbach.android.UI.RecyclerAdapter.TypeIcon
import com.hemmersbach.android.ViewModel.RemoteViewModel
import com.hemmersbach.android.ViewModel.RemoteViewModelFactory
import kotlinx.android.synthetic.main.fragment_remote.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.kodein
import org.kodein.di.generic.instance

class Remote : Fragment(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val remoteViewModelFactory: RemoteViewModelFactory by instance()
    private lateinit var remoteViewModel: RemoteViewModel
    private val mAdapter = RemoteRecycler(TypeIcon.REMOTE)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_remote, container, false)
        remoteViewModel =
            ViewModelProviders.of(this, remoteViewModelFactory).get(RemoteViewModel::class.java)
        initializeView(view)
        getJokes()
        getError(view)
        addJokeToDataBase()
        return view
    }

    private fun initializeView(view: View) {
        view.remoteRecyclerView.adapter = mAdapter
        view.swipeLayout.setOnRefreshListener {
            startFetchingRemoteData()
            unableRefreshing(view)
        }
    }

    private fun getJokes() =
        remoteViewModel.joke.observe(this, Observer { mAdapter.swapData(it!!) })

    private fun getError(view: View) =
        remoteViewModel.error.observe(this, Observer { initializeSnackBar(view, it!!) })

    private fun initializeSnackBar(view: View, message: String) =
        Snackbar.make(
            view.swipeLayout,
            message,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(getString(R.string.Retry)) {
            startFetchingRemoteData()
        }.show()

    private fun startFetchingRemoteData() = remoteViewModel.getJokes()

    private fun unableRefreshing(view: View) {
        view.swipeLayout.isRefreshing = false
    }

    private fun addJokeToDataBase() {
        mAdapter.addToDataBase = { newValue, oldValue ->
            remoteViewModel.addJoke(newValue)
            mAdapter.removeJoke(oldValue)
        }
    }
}
