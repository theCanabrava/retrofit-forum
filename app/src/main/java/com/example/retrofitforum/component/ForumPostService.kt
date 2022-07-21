package com.example.retrofitforum.component

import retrofit2.Call
import retrofit2.http.*

interface ForumPostService
{
    @GET("posts/{postId}")
    fun get(@Path("postId") postId: Long): Call<ForumPost>

    @GET("posts")
    fun getAll(): Call<ArrayList<ForumPost>>

    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Long): Call<User>

    @POST("posts")
    fun post(@Body post: CreateForumPostBody): Call<ForumPost>

    @PATCH("posts/{postId}")
    fun patch(
        @Body post: PatchForumPostBody,
        @Path("postId") postId: Long
    ) : Call<ForumPost>

    @DELETE("posts/{postId}")
    fun delete(@Path("postId") postId: Long): Call<EmptyResponse>
}