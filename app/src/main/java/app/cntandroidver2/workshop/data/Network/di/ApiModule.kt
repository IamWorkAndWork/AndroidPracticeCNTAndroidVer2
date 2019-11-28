//package app.cntandroidver2.workshop.data.Network.di
//
//import app.cntandroidver2.workshop.data.Network.APIService
//import dagger.Module
//import dagger.Provides
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Named
//import javax.inject.Singleton
//
//
//@Module
//class ApiModule {
//
//    @Provides
//    @Singleton
//    @Named("baseUrl")
//    fun BASE_URL(): String = "http://34.82.250.142/temp/jsondatas.html"
//
//    @Provides
//    fun provideRetrofit(@Named("baseUrl") baseUrl: String, client: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//
//    }
//
//    @Provides
//    fun provideClient(): OkHttpClient {
//        val interceptor = HttpLoggingInterceptor()
//        return OkHttpClient.Builder().addInterceptor(interceptor).build()
//    }
//
//    @Provides
//    fun provideApiService(retrofit: Retrofit): APIService {
//        return retrofit.create(APIService::class.java)
//    }
//
//
//}