package com.example.retrofitforum.post

import androidx.lifecycle.LifecycleOwner
import com.example.retrofitforum.component.ForumManager
import com.example.retrofitforum.component.ForumPost
import com.example.retrofitforum.shared.DefaultForumModel

class PostViewModel(private val manager: ForumManager): DefaultForumModel(manager)
{
    fun observe(owner: LifecycleOwner, callback: (ForumPost)->(Unit))
    {
        manager.monitored.observe(owner) { callback(it) }
    }

    fun delete()
    {
        manager.delete(manager.monitored.value!!)
    }
}