package com.example.marvelsworld.provider

import com.example.marvelsworld.response.CharactersResponse

interface MarvelProvider {

   suspend fun getCharacter(): CharactersResponse
}