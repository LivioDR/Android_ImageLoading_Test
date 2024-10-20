package com.example.project01.reinoso1165606.networkingtest.view

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.project01.reinoso1165606.networkingtest.R
import com.example.project01.reinoso1165606.networkingtest.services.CustomImageLoader
import com.example.project01.reinoso1165606.networkingtest.viewModel.PokeViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val pokeId:PokeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // getting the label that will display the pokemon name
        val label = findViewById<TextView>(R.id.pokemonName)

        // getting the image view that will display the GIF
        val image = findViewById<ImageView>(R.id.pokeImage)

        // getting the buttons to interact with the UI
        val prevBtn = findViewById<Button>(R.id.prevPoke)
        val nextBtn = findViewById<Button>(R.id.nextPoke)

        // adding the functionality to the buttons
        prevBtn.setOnClickListener {
            pokeId.decrease()
        }
        nextBtn.setOnClickListener {
            pokeId.increase()
        }

        // creating an instance of the image loader class
        val imageSetter = CustomImageLoader(this)

        pokeId.getId().observe(this) { pokemonId ->
            // and finally requesting the data
            imageSetter.requestImage(pokemonId,image,this)
        }



    }


}