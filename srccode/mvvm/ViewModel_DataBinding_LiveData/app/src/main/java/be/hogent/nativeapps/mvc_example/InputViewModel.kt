package be.hogent.nativeapps.mvc_example

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class InputViewModel : ViewModel() { //, Observable {
    var userInput = MutableLiveData<String>()

    init {
        //If we don't set it as empty initially, it will be displayed as null
        userInput.value = ""
    }
}