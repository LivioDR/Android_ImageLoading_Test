package com.example.project01.reinoso1165606.networkingtest.services

import com.example.project01.reinoso1165606.networkingtest.model.PokeApiResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitService {
    // definition of the function getPokemonData as a GET request
    @GET("pokemon/{id}")
    // declaring this as a suspend function to run in a coroutine
    suspend fun getPokemonData(
        // all Query parameters are passed as arguments to the function
        // if we needed to pass path variables we can use @Path instead
        @Path("id") id:Int,
        // which will then return a RemoteResult instance as defined in the model folder
        ): PokeApiResult
}

// this object will be called from the main activity to create an instance
// of the RetrofitService declared as an interface above
object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            // we pass the base URL to the Retrofit builder
            .baseUrl("https://pokeapi.co/api/v2/")
            // a converter factory to convert between JSON and objects
            .addConverterFactory(GsonConverterFactory.create())
            // and then we call the build method to create the instance of this class
            .build().create(RetrofitService::class.java)
    }
}