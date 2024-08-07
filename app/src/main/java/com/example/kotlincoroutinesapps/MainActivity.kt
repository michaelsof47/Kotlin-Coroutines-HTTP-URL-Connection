package com.example.kotlincoroutinesapps

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.kotlincoroutinesapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UrlViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(UrlViewModel::class.java)

        binding.btnAsynctask.setOnClickListener {
            binding.tvLabel.visibility = View.GONE
            binding.progressLoading.visibility = View.VISIBLE
            viewModel.urlRetrieve()
        }

        viewModel.setUrlResp().observe(this) {
            binding.progressLoading.visibility = View.GONE
            binding.tvLabel.visibility = View.VISIBLE
            binding.tvLabel.text = it
        }
    }
}