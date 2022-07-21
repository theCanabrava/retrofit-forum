package com.example.retrofitforum.component

import androidx.lifecycle.LiveData

interface ForumManager
{
    enum class State { IDLE, LOADING, ERROR }
    val monitored: LiveData<ForumPost>
    val list: LiveData<ArrayList<ForumPost>>
    val state: LiveData<State>

    fun getAll()
    fun get(postId: Long)

    fun add(post: CreateForumPostBody)
    fun edit(post: PatchForumPostBody)
    fun delete(post: ForumPost)

    fun filter(userId: Int)
}