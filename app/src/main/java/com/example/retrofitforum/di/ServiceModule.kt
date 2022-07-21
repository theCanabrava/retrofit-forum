package com.example.retrofitforum.di

import com.example.retrofitforum.component.ForumManager
import com.example.retrofitforum.component.RetrofitForumManager
import org.koin.dsl.module


val serviceModule = module {
    single<ForumManager> { RetrofitForumManager() }
}