package com.giniapps.testappkotlin.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _events = MutableSharedFlow<String>() // private mutable shared flow
    val events = _events.asSharedFlow() // publicly exposed as read-only shared flow

    private suspend fun produceEvent(event: String) {
        _events.emit(event)
    }

    init {
        viewModelScope.launch {
            var index = 0
            while (true) {
                produceEvent("new event $index")
                delay(200);
                index += 1
            }
        }
    }

}