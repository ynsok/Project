package com.hemmersbach.android.viewModel

import androidx.lifecycle.ViewModel
import com.hemmersbach.android.repository.Repository

class LocalViewModel(private val repository: Repository) : ViewModel()