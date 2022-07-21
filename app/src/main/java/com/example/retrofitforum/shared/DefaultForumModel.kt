package com.example.retrofitforum.shared

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.retrofitforum.component.ForumManager

open class DefaultForumModel(private val manager: ForumManager): ViewModel()
{
    fun observeState(
        owner: LifecycleOwner,
        onIdle: ()->Unit,
        onLoading: ()->Unit,
        onError: ()->Unit
    )
    {
        manager.state.observe(owner) {
            when (it)
            {
                ForumManager.State.IDLE -> onIdle()
                ForumManager.State.LOADING -> onLoading()
                ForumManager.State.ERROR -> onError()
                else -> onIdle()
            }
        }
    }
}