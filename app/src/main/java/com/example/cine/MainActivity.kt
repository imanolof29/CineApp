package com.example.cine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cine.databinding.ActivityMainBinding
import com.example.cine.models.Movie
import com.example.cine.retrofit.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding:ActivityMainBinding

    private lateinit var movieAdapter: MovieAdapter

    private lateinit var movies:MutableList<Movie>

    private lateinit var matchedMovies:MutableList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        performSearch()
    }

    private fun initRecyclerView(){

        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(MovieService::class.java).getMovies()
            runOnUiThread{
                if(call.isSuccessful){
                    movies = call.body() as MutableList<Movie>
                    movieAdapter = MovieAdapter(movies)
                    binding.recyclerview.setHasFixedSize(true)
                    binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.recyclerview.adapter = movieAdapter
                }else{
                    Toast.makeText(this@MainActivity, "Algo fue muy mal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.37:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun performSearch(){
        binding.svMovies.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText)
                return true
            }
        })
    }

    private fun search(query:String?) {
        matchedMovies = mutableListOf<Movie>()

        query?.let {
            movies.forEach{ movie ->
                if(movie.title.contains(query, true)){
                    matchedMovies.add(movie)
                    updateRecyclerView()
                }
            }
            if(matchedMovies.isEmpty()){
                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show()
            }
            updateRecyclerView()
        }
    }

    private fun updateRecyclerView(){
        binding.recyclerview.apply {
            movieAdapter.movies = matchedMovies
            movieAdapter.notifyDataSetChanged()
        }
    }

    override fun onItemClick(position: Int) {
        val details = Intent(this, DetailsActivity::class.java)
        details.putExtra("title", movies[position].title)
        details.putExtra("image", movies[position].imageURL)
        startActivity(details)
    }


}