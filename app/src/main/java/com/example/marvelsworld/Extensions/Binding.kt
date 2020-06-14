package com.example.marvelsworld.Extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any> LifecycleOwner.bind(data: LiveData<T>, function: (id: T) -> Unit) {
    data.observe(this, Observer { value ->
        value?.let {
            function.invoke(value)
        }
    })
}