package com.example.retrofitforum.post

import androidx.lifecycle.ViewModel
import com.example.retrofitforum.component.ForumManager
import com.example.retrofitforum.component.ForumPost
import com.example.retrofitforum.component.PatchForumPostBody

class EditViewModel(private val manager: ForumManager): ViewModel()
{
    val monitored: ForumPost get() = manager.monitored.value!!
    fun edit(title: String, message: String)
    {
        val post = manager.monitored.value!!
        val edited = PatchForumPostBody(
            post.id,
            post.userId,
            title,
            message
        )
        manager.edit(edited)
    }
}