package app.cntandroidver2.workshop.ui.tab2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Tab2ViewModel : ViewModel() {

    private val _textIamJack = MutableLiveData<String>().apply {
        value = "I am jack"
    }
    val textIamJack: LiveData<String> = _textIamJack
}