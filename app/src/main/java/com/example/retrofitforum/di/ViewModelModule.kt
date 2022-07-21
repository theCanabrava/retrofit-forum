package com.example.retrofitforum.di

import com.example.retrofitforum.main.MainViewModel
import com.example.retrofitforum.post.AddViewModel
import com.example.retrofitforum.post.EditViewModel
import com.example.retrofitforum.post.PostViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{ MainViewModel(get()) }
    viewModel{ PostViewModel(get()) }
    viewModel{ EditViewModel(get()) }
    viewModel{ AddViewModel(get()) }
}