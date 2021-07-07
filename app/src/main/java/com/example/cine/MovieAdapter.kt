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

class MovieAdapter(var movies:List<Movie>, val listener: OnItemClickListener) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val title: TextView = view.findViewById(R.id.tvTitle)
        val rating: RatingBar = view.findViewById(R.id.movieRating)
        val image: ImageView = view.findViewById(R.id.image)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position!=RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = movies[position].title
        holder.rating.rating = movies[position].rating.toFloat()
        //get the image via url
        Picasso.get()
            .load(movies[position].imageURL)
            .into(holder.image)

    }

    override fun getItemCount(): Int = movies.size
}

