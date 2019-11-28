package app.cntandroidver2.workshop.ui.tab1

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.cntandroidver2.workshop.data.DataRepository
import app.cntandroidver2.workshop.data.Model.BookData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Tab1ViewModel(private val repository: DataRepository) : ViewModel() {

    val TAG = "Tab1ViewModel"
    private var _bookData = MutableLiveData<BookData>()
    private var _bookLoadingStatus = MutableLiveData<Boolean>()

    val bookData: LiveData<BookData> = _bookData
    val bookStatus: LiveData<Boolean> = _bookLoadingStatus


    fun fetchBookAsync() {

        Log.d(TAG, "call getBooksAsync start")

        repository.getBooksAsync().enqueue(object : Callback<BookData> {
            override fun onFailure(call: Call<BookData>, t: Throwable) {
                _bookLoadingStatus.value = false
                Log.d(TAG, "call getBooksAsync error = " + t)

            }

            override fun onResponse(call: Call<BookData>, response: Response<BookData>) {
                _bookLoadingStatus.value = true
                if (response.isSuccessful) {
                    _bookData.value = response.body()
                    Log.d(TAG, "call getBooksAsync success")

                } else {
                    _bookLoadingStatus.value = false
                    Log.d(TAG, "call getBooksAsync connected but fauled : " + response.raw())

                }
            }
        })

    }

    @SuppressLint("CheckResult")
    fun fetchBookFromServerRx() {

        Log.d(TAG, "call fetchBookFromServer start")

        repository.getBooksRx().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _bookData.value = it
                _bookLoadingStatus.value = true

                Log.d(TAG, "call fetchBookFromServer success")
            }, {
                _bookLoadingStatus.value = false
                Log.d(TAG, "getBooks Error = " + it)
            })
    }

    fun fetchBookFromServerCoroutine() {

        Log.d(TAG, "call fetchBookFromServerCoroutine")

        GlobalScope.launch(Dispatchers.Main) {

            try {
//                val data = withContext(Dispatchers.IO) {
//                    repository.getBooksCoroutine()
//                }

                val data = async {
                    repository.getBooksCoroutine()
                }.await()

                Log.d(TAG, "call fetchBookFromServerCoroutine success")

                _bookData.value = data
                _bookLoadingStatus.value = true

            } catch (e: Exception) {
                Log.d(TAG, "call fetchBookFromServerCoroutine error = " + e)
                _bookLoadingStatus.value = false
            }
        }
    }

    class Factory(private val repository: DataRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Tab1ViewModel(repository) as T
        }

    }
}