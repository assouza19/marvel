package com.example.marvelsworld.koin

import com.example.marvelsworld.business.MarvelBusiness
import com.example.marvelsworld.business.MarvelBusinessImpl
import com.example.marvelsworld.provider.MarvelProvider
import com.example.marvelsworld.provider.MarvelProviderImpl
import com.example.marvelsworld.retrofit.MarvelWorldRetrofit
import com.example.marvelsworld.viewModel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get(), Dispatchers.Main, get()) }

    factory { MarvelBusinessImpl(get()) } bind MarvelBusiness::class

    factory { MarvelProviderImpl(get()) } bind MarvelProvider::class

    factory { MarvelWorldRetrofit() }
}