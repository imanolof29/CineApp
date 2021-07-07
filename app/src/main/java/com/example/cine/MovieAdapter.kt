package com.example.cine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cine.models.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(var movies:List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val movieRating: RatingBar
        //val overview: TextView
        val image: ImageView

        init {
            title = view.findViewById(R.id.tvTitle)
            movieRating = view.findViewById(R.id.movieRating)
            //overview = view.findViewById(R.id.txtOverview)
            image = view.findViewById(R.id.image)
            //itemView.setOnClickListener(this)
        }

        /*override fun onClick(v: View?) {
            val position = adapterPosition
            if(position!=RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = movies[position].title
        holder.movieRating.rating = movies[position].rating/2
        //holder.overview.text = movies[position].overview
        //get the image via url
        Picasso.get()
            .load(movies[position].imageURL)
            .into(holder.image)

    }

    override fun getItemCount(): Int = movies.size
}

interface OnItemClickListener{
    fun onItemClick(position: Int)
}