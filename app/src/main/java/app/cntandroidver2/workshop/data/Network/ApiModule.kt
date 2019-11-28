package app.cntandroidver2.workshop.data.Network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ApiModule {

    val BASE_URL = "http://34.82.250.142/"//temp/jsondatas.html

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun provideClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    fun provideApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }


}