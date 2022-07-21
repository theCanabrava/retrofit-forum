package com.example.retrofitforum.post

import com.example.retrofitforum.component.CreateForumPostBody
import com.example.retrofitforum.component.ForumManager
import com.example.retrofitforum.shared.DefaultForumModel

class AddViewModel(private val manager: ForumManager): DefaultForumModel(manager)
{
    fun add(title: String, body: String)
    {
        val id = manager.list.value!!.size.toLong()
        val post = CreateForumPostBody(id, 1, title, body)
        manager.add(post)
    }

}