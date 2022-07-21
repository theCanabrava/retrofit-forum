package com.example.retrofitforum.main

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import com.example.retrofitforum.R
import com.example.retrofitforum.component.ForumPost
import com.example.retrofitforum.databinding.ActivityMainBinding
import com.example.retrofitforum.post.AddActivity
import com.example.retrofitforum.post.PostActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PostAdapter
    private val model: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.observeList(this) {
            adapter.posts = it
            adapter.notifyDataSetChanged()
        }

        model.observeState(this, {this.onIdle()}, {this.onLoading()}, {this.onError()})
        model.loadHome()
        bindList()
        bindRefresh()
    }

    private fun bindList()
    {
        adapter = PostAdapter(this, model.posts)
        binding.posts.adapter = adapter
        binding.posts.setOnItemClickListener { _, _, _, id ->
            model.select(id)
            startActivity(Intent(this, PostActivity::class.java))
        }
    }

    private fun bindRefresh()
    {
        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = false
            model.loadHome()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.addTask -> startActivity(Intent(this, AddActivity::class.java))
            R.id.filter -> model.filter()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onIdle()
    {
        binding.mainError.isVisible = false
        binding.mainLoading.isVisible = false
        binding.posts.isVisible = true
    }

    private fun onLoading()
    {
        binding.mainError.isVisible = false
        binding.mainLoading.isVisible = true
        binding.posts.isVisible = false
    }

    private fun onError()
    {
        binding.mainError.isVisible = true
        binding.mainLoading.isVisible = false
        binding.posts.isVisible = false
    }
}