package com.example.myfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import org.w3c.dom.Text
import java.io.InputStreamReader
import java.util.*

class Movie(

    val title: String,
    val year: Int,
    val directedBy: String,
    val genre: String,
    val rate: String,
    val time: String
)

class MovieList(

    val films: ArrayList<Movie>
)

class MainActivity : AppCompatActivity() {

    private var movies: ArrayList<Movie> = arrayListOf()
    private var countmovies = 0

    private fun showMovie(movie: Movie) {

        val movieTitleView: TextView = findViewById(R.id.movieTitle)
        val movieYearView: TextView = findViewById(R.id.movieYear)
        val movieDirectedByView: TextView = findViewById(R.id.movieDirectedBy)
        val movieGenreView: TextView = findViewById(R.id.movieGenre)
        val movieRateView: TextView = findViewById(R.id.movieRate)
        val movieTimeView: TextView = findViewById(R.id.movieTime)

        movieTitleView.text = movie.title
        movieYearView.text = movie.year.toString()
        movieDirectedByView.text = movie.directedBy
        movieGenreView.text = movie.genre
        movieRateView.text = movie.rate
        movieTimeView.text = movie.time
    }

    fun btnStart(view: View) {

        when (countmovies) {
            movies.size -> {
                setShowMovie(View.INVISIBLE)
                movies.shuffle()
                Snackbar.make(view, "that's all", Snackbar.LENGTH_SHORT).show()
                countmovies = -1
            }
            else -> {
                setShowMovie(View.VISIBLE)
                showMovie(movies[countmovies])
            }
        }
        countmovies += 1
    }

    private fun readMovies() {

        val movieStream = resources.openRawResource(R.raw.films)
        val gson = Gson()
        val filmList = gson.fromJson(InputStreamReader(movieStream), MovieList::class.java)
        movies = filmList.films
        movies.shuffle()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        readMovies()
        setShowMovie(View.INVISIBLE)
    }

    private fun setShowMovie(visibily: Int) {

        val setArea = arrayOf(R.id.movieTitle,
            R.id.movieYear,
            R.id.movieDirectedBy,
            R.id.movieGenre,
            R.id.movieRate)

        for (id in setArea) {
            val view: View = findViewById(id)
            view.visibility = visibily
            Snackbar.make(view, "click the button", Snackbar.LENGTH_SHORT).show()
        }
    }
}