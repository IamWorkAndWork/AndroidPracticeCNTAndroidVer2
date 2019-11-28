package app.cntandroidver2.workshop.data.Network

import app.cntandroidver2.workshop.data.Model.BookData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("temp/jsondatas.html")
    fun getJSONAsync(): Call<BookData>

    @GET("temp/jsondatas.html")
    fun getJsonDatas(): Observable<BookData>

    @GET("temp/jsondatas.html")
    suspend fun getJsonDatasCoroutine(): BookData
}