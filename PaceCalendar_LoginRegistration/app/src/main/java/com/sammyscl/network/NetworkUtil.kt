

package com.sammyscl.network

import android.util.Base64

import com.sammyscl.Helpers.Constants

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers

object NetworkUtil {

    val retrofit: RetrofitInterface
        get() {

            val rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io())

            return Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addCallAdapterFactory(rxAdapter)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(RetrofitInterface::class.java!!)
        }

    fun getRetrofit(email: String, password: String): RetrofitInterface {

        val credentials = "$email:$password"
        val basic = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->

            val original = chain.request()
            val builder = original.newBuilder()
                    .addHeader("Authorization", basic)
                    .method(original.method(), original.body())
            chain.proceed(builder.build())

        }

        val rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io())

        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpClient.build())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitInterface::class.java!!)
    }

    fun getRetrofit(token: String): RetrofitInterface {

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->

            val original = chain.request()
            val builder = original.newBuilder()
                    .addHeader("x-access-token", token)
                    .method(original.method(), original.body())
            chain.proceed(builder.build())

        }

        val rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io())

        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpClient.build())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitInterface::class.java!!)
    }
}
