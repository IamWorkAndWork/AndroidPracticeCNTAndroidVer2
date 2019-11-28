package app.cntandroidver2.workshop.data

import app.cntandroidver2.workshop.data.Model.BookData
import app.cntandroidver2.workshop.data.Network.BookDataSource
import io.reactivex.Observable
import retrofit2.Call

class DataRepository(private var bookDataSource: BookDataSource) {

    fun getBooksAsync(): Call<BookData> {
        return bookDataSource.getBooksAsync()
    }

    fun getBooksRx(): Observable<BookData> {
        return bookDataSource.getBooks()
    }

    suspend fun getBooksCoroutine(): BookData {
        return bookDataSource.getBooksCoroutine()
    }

}