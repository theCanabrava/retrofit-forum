package com.example.retrofitforum.component

import java.io.Serializable

class ForumPost (
    val id: Long,
    val userId: Int,
    val title: String,
    val body: String
): Serializable

class CreateForumPostBody (
    val id: Long,
    val userId: Int,
    val title: String,
    val body: String
): Serializable

class PatchForumPostBody (
    val id: Long,
    val userId: Int?,
    val title: String?,
    val body: String?
): Serializable

class EmptyResponse: Serializable

class User (
    val id: Long,
    val name: String,
    val username: String
): Serializable