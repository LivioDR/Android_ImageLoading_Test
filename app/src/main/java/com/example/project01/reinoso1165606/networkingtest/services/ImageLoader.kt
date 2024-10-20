package com.example.project01.reinoso1165606.networkingtest.services

import android.content.Context
import android.widget.ImageView
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.target

class CustomImageLoader (val context: Context):SingletonImageLoader.Factory {

    // setting the image loader
    val imageLoader = newImageLoader(context)

    fun requestImage(id:Int, container: ImageView, context:Context){
        // creating the request to be passed to the image loader
        val request = ImageRequest.Builder(context)
            .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/${id}.gif")
            .crossfade(true)
            .target(container)
            .build()

        // loading the image
        imageLoader.enqueue(request)
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(context)
            .crossfade(true)
            .build()
    }


}