package com.example.marvelsworld.business

import com.example.marvelsworld.provider.MarvelProvider

class MarvelBusinessImpl(private val provider: MarvelProvider) : MarvelBusiness {

    override suspend fun getUsers() = provider.getCharacter()

}