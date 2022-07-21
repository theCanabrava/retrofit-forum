package com.example.retrofitforum.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitforum.R
import com.example.retrofitforum.databinding.ActivityEditBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddActivity: AppCompatActivity()  {

    private lateinit var binding: ActivityEditBinding
    private val model: AddViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.confirmEdit.text = getString(R.string.confirm_add)
        binding.confirmEdit.setOnClickListener {
            model.add(
                binding.editTitle.text.toString(),
                binding.editBody.text.toString()
            )
            finish()
        }
    }
}