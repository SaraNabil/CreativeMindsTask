package com.example.creativemindstask.Network

import android.util.Log
import com.example.creativemindstask.MyApp
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    const val HEADER_CACHE_CONTROL = "Cache-Control"
    const val HEADER_PRAGMA = "Pragma"
    private const val BASE_URL = "https://api.github.com/users/square/"
    private const val cacheSize = 5 * 1024 * 1024.toLong() // 5 MB
    private var instance: ServiceBuilder? = null

    fun getInstance(): ServiceBuilder? {
        if (instance == null) {
            instance = ServiceBuilder
        }
        return instance
    }

    private fun retrofit(): Retrofit? {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient()!!)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun okHttpClient(): OkHttpClient? {
        return OkHttpClient.Builder()
            .cache(cache())
            .addInterceptor(httpLoggingInterceptor()!!) // used if network off OR on
            .addNetworkInterceptor(networkInterceptor()!!) // only used when network is on
            .addInterceptor(offlineInterceptor()!!)
            .build()
    }

    private fun cache(): Cache? {
        return Cache(
            File(MyApp.getInstance()?.cacheDir, "responseCache"),
            cacheSize
        )
    }


    /**
     * This interceptor will be called both if the network is available and if the network is not available
     *
     * @return
     */
    private fun offlineInterceptor(): Interceptor? {
        return Interceptor { chain: Interceptor.Chain ->
            var request = chain.request()

            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (!MyApp.getInstance()?.hasNetwork()!!) {
                val cacheControl = CacheControl.Builder()
                    // if the data in cache valid for 1h at max will get it
                    .maxStale(1, TimeUnit.HOURS)
                    .build()
                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }


    /**
     * This interceptor will be called ONLY if the network is available
     *
     * @return
     */
    private fun networkInterceptor(): Interceptor? {
        return Interceptor { chain: Interceptor.Chain ->
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.HOURS)
                .build()
            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    // this is just for logging the response for debugging purposes
    private fun httpLoggingInterceptor(): HttpLoggingInterceptor? {
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("log:", "log: http log: $message")
            }
        })
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }

    fun getApi(): EndPointInterface? {
        return retrofit()?.create(EndPointInterface::class.java)
    }
}