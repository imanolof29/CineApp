package com.example.cine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cine.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Picasso.get()
            .load(intent.getStringExtra("image"))
            .into(binding.detailsImage)

        binding.tvDetailsTitle.text = intent.getStringExtra("title")
        binding.tvOverview.text = intent.getStringExtra("overview")
        binding.ratingBar.rating = intent.getFloatExtra("rating", 0.0f)
    }
}