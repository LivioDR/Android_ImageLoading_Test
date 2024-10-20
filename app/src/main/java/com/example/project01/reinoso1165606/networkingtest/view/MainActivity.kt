package com.example.project01.reinoso1165606.networkingtest.view

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.project01.reinoso1165606.networkingtest.R
import com.example.project01.reinoso1165606.networkingtest.services.CustomImageLoader
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var pokeId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // getting the image view that will display the GIF
        val image = findViewById<ImageView>(R.id.pokeImage)

        // creating an instance of the image loader class
        val imageSetter = CustomImageLoader(this)

        // and finally requesting the data
        imageSetter.requestImage(pokeId,image,this)

    }


}