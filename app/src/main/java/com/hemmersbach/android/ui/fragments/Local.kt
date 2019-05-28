package com.hemmersbach.android.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.hemmersbach.android.R
import com.hemmersbach.android.viewModel.LocalViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class Local : Fragment() {

    private lateinit var localViewModel: LocalViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment, container, false)
}