package com.example.daniel.discoveritunes_02.model.api

import com.example.daniel.discoveritunes_02.BuildConfig
import com.example.daniel.discoveritunes_02.model.ITunesResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import rx.Scheduler
import rx.schedulers.Schedulers

/**
 * Created by Daniel on 5/29/17.
 */
interface RetrofitService {
    @GET("search")
    fun search(
            @Query("term") term : String,
            @Query("entity") entity : String
    ) : Observable<ITunesResult>

    /**
     * Companion object to create the service from Result class
     */
    companion object Factory {
        val BASE_URL = BuildConfig.BASE_URL
        fun create() : RetrofitService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(RetrofitService::class.java)
        }
    }
}

/*
Previous code for the call with only retrofit and gson:
@GET("search")
    fun getSearch(
            @Query("term") term : String,
            @Query("entity") entity : String
    ) : Call<ITunesResult>

val BASE_URL = BuildConfig.BASE_URL
        fun create() : RetrofitService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(RetrofitService::class.java)
        }

 */