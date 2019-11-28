package app.cntandroidver2.workshop.data.Network

import app.cntandroidver2.workshop.data.Model.BookData
import io.reactivex.Observable
import retrofit2.Call

class BookDataSource(private val apiModule: ApiModule) {

    fun getBooksAsync(): Call<BookData> {
        val client = apiModule.provideClient()
        val retrofit = apiModule.provideRetrofit(client)
        val service = apiModule.provideApiService(retrofit).getJSONAsync()
        return service
    }

    fun getBooks(): Observable<BookData> {
        val client = apiModule.provideClient()
        val retrofit = apiModule.provideRetrofit(client)
        val service = apiModule.provideApiService(retrofit).getJsonDatas()
        return service
    }

    suspend fun getBooksCoroutine(): BookData {
        val client = apiModule.provideClient()
        val retrofit = apiModule.provideRetrofit(client)
        val service = apiModule.provideApiService(retrofit).getJsonDatasCoroutine()
        return service
    }



}