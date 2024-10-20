package com.example.project01.reinoso1165606.networkingtest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project01.reinoso1165606.networkingtest.model.PokeApiResult
import com.example.project01.reinoso1165606.networkingtest.services.RetrofitServiceFactory
import kotlinx.coroutines.launch

class PokeViewModel:ViewModel() {

    private var pokemonId = MutableLiveData<Int>()
    private var pokemonInfo = MutableLiveData<PokeApiResult>()

    val service = RetrofitServiceFactory.makeRetrofitService()

    fun getId():LiveData<Int> = pokemonId

    fun increase(){
        pokemonId.value = pokemonId.value?.plus(1)
    }

    fun decrease(){
        if(pokemonId.value?.toInt()!! > 1){
            pokemonId.value = pokemonId.value?.minus(1)
        }
    }

    fun updatePokemonData(){
        val id = pokemonId.value
        viewModelScope.launch {
            pokemonInfo.value = id?.let { service.getPokemonData(it) }
        }
    }

    fun getInfo():LiveData<PokeApiResult> = pokemonInfo

    init {
        pokemonId.value = 1
        updatePokemonData()
    }
    override fun onCleared() {
        super.onCleared()
    }
}