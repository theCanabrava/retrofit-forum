package com.example.retrofitforum.post

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.retrofitforum.R
import com.example.retrofitforum.databinding.ActivityPostBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding
    private val model: PostViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)

        setContentView(binding.root)

        model.observe(this)
        {
            binding.postTitle.text = getString(R.string.title, it.title)
            binding.postBody.text = getString(R.string.title, it.body)
        }

        model.observeState(this, {onIdle()}, {onLoading()}, {onError()})

        binding.deletePost.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(R.string.delete_title)
                .setMessage(R.string.sure)
                .setPositiveButton(R.string.confirm) { dialog, _ ->
                    dialog.dismiss()
                    model.delete()
                    finish()
                }
                .setNegativeButton(R.string.cancel) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
        binding.editPost.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }
    }

    private fun onIdle()
    {
        binding.postError.isVisible = false
        binding.postLoading.isVisible = false
        binding.postLayout.isVisible = true
    }

    private fun onLoading()
    {
        binding.postError.isVisible = false
        binding.postLoading.isVisible = true
        binding.postLayout.isVisible = false
    }

    private fun onError()
    {
        binding.postError.isVisible = true
        binding.postLoading.isVisible = false
        binding.postLayout.isVisible = false
    }
}