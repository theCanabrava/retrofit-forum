package com.example.retrofitforum.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.retrofitforum.component.ForumManager
import com.example.retrofitforum.component.ForumPost
import com.example.retrofitforum.shared.DefaultForumModel

class MainViewModel(private val manager: ForumManager) : DefaultForumModel(manager)
{
    val posts get() = manager.list.value!!
    fun loadHome() { manager.getAll() }
    fun select(postId: Long) { manager.get(postId) }

    fun observeList(owner: LifecycleOwner, callback: (ArrayList<ForumPost>) -> Unit)
    {
        manager.list.observe(owner) { callback(it) }
    }

    fun filter() {manager.filter(1)}
}