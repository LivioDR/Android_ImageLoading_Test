package com.example.project01.reinoso1165606.networkingtest.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project01.reinoso1165606.networkingtest.R
import com.example.project01.reinoso1165606.networkingtest.services.CustomImageLoader
import com.example.project01.reinoso1165606.networkingtest.viewModel.PokeViewModel

class MainActivity : AppCompatActivity() {

    private val pokemon:PokeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // getting the UI elements
        val image = findViewById<ImageView>(R.id.pokeImage)
        val prevBtn = findViewById<Button>(R.id.prevPoke)
        val nextBtn = findViewById<Button>(R.id.nextPoke)
        val spinner = findViewById<ProgressBar>(R.id.spinner)

        // adding the functionality to the buttons
        prevBtn.setOnClickListener {
            pokemon.decrease()
        }
        nextBtn.setOnClickListener {
            pokemon.increase()
        }

        // creating an instance of the image loader class
        val imageSetter = CustomImageLoader(this)

        pokemon.getId().observe(this) { pokemonId ->
            // show the spinner
            spinner.visibility = View.VISIBLE

            // update the pokemon info in the view model
            val pokeInfo = pokemon.updatePokemonData()

        }

        pokemon.getInfo().observe(this) { pokeInfo ->
            // setting the name as the title
            val actionBar = supportActionBar
            actionBar?.setTitle(pokeInfo.name)

            val sprite = pokeInfo.sprites.other.showdown.front_default

            // and finally requesting the image data
            imageSetter.requestImage(sprite,image,this)

            // hide the spinner
            spinner.visibility = View.GONE
        }


    }


}