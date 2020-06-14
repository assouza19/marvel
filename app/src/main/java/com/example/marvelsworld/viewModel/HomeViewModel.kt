package com.example.marvelsworld.viewModel

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelsworld.Extensions.Event
import com.example.marvelsworld.Extensions.triggerEvent
import com.example.marvelsworld.R
import com.example.marvelsworld.business.MarvelBusiness
import com.example.marvelsworld.response.Result
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val business: MarvelBusiness,
    private val dispatcher: CoroutineContext,
    private val context: Context
) : ViewModel() {

    private var internalProgressBarVisibility = MutableLiveData<Int>().apply { value = View.GONE }
    private var internalRecyclerVisibility = MutableLiveData<Int>().apply { value = View.GONE }
    private var internalError = MutableLiveData<Event<String>>()
    private var internalSuccess = MutableLiveData<Event<List<Result>>>()
    private var listUsers = listOf<Result>()

    val progressBarVisibility: LiveData<Int>
        get() = internalProgressBarVisibility
    val recyclerVisibility: LiveData<Int>
        get() = internalRecyclerVisibility
    val error: LiveData<Event<String>>
        get() = internalError
    val success: LiveData<Event<List<Result>>>
        get() = internalSuccess

    fun init() {
        internalProgressBarVisibility.value = View.VISIBLE
        internalRecyclerVisibility.value = View.GONE

        viewModelScope.launch(dispatcher) {
            listUsers = business.getUsers().data.results
            callExecuted()
        }
    }

    private fun callExecuted() {
        if (listUsers.isEmpty()) {
            internalError.triggerEvent(context.getString(R.string.error))
        } else {
            showList()
        }
        internalProgressBarVisibility.value = View.GONE
    }

    private fun showList() {
        internalSuccess.triggerEvent(listUsers)
        internalRecyclerVisibility.value = View.VISIBLE
    }

}
