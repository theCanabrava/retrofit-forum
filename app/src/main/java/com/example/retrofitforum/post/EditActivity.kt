package com.example.retrofitforum.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitforum.R
import com.example.retrofitforum.databinding.ActivityEditBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private val model: EditViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.editTitle.setText(model.monitored.title)
        binding.editBody.setText(model.monitored.body)
        binding.confirmEdit.setOnClickListener {
            model.edit(binding.editTitle.text.toString(), binding.editBody.text.toString())
            finish()
        }
    }
}