package com.example.retrofitforum.main

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.retrofitforum.component.ForumPost


class PostAdapter(
    private val context: Context,
    var posts: ArrayList<ForumPost>
    ): BaseAdapter()
{
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount() = posts.size

    override fun getItem(position: Int) = posts[position]

    override fun getItemId(position: Int): Long = posts[position].id

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View
    {
        val rowView = inflater.inflate(R.layout.simple_list_item_1, viewGroup, false)
        rowView.findViewById<TextView>(R.id.text1).text = posts[position].title
        return rowView
    }

}