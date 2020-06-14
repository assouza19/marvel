package com.example.marvelsworld.business

import com.example.marvelsworld.response.CharactersResponse

interface MarvelBusiness {

   suspend fun getUsers(): CharactersResponse
}