package com.example.project01.reinoso1165606.networkingtest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PokeViewModel:ViewModel() {

    private var pokemonId = MutableLiveData<Int>(1)

    fun getId():LiveData<Int> = pokemonId

    fun increase(){
        pokemonId.value = pokemonId.value?.plus(1)
    }

    fun decrease(){
        if(pokemonId.value?.toInt()!! > 1){
            pokemonId.value = pokemonId.value?.minus(1)
        }
    }

    init {

    }
    override fun onCleared() {
        super.onCleared()
    }
}