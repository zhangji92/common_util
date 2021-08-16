package com.zj.common.http

import com.zj.common.expand.isConnected
import com.zj.common.utils.Utils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * createTime:2021/8/3 11:12
 * auth:张继
 * des:
 */
class HttpClient private constructor() {

    private val baseUrl: String by lazy { "https://www.wanandroid.com/" }
    //private val baseUrl: String by lazy { "https://shejiao.broyoung.com" }


    companion object {
        private val client: HttpClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HttpClient()
        }

        fun get(): HttpClient {
            return client
        }

        val retrofit: Retrofit
            get() = get().initRetrofit
    }

    private val initRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(build)
            //.addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val build: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor {
            //it.printfLog()
        }
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(provideOfflineCacheInterceptor())
            .addNetworkInterceptor(provideCacheInterceptor())
            .hostnameVerifier { _, _ -> true }
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .pingInterval(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .followRedirects(true)
            .build()
    }

    /**
     * 离线缓存
     *
     * @return 返回缓存拦截器
     */
    private fun provideOfflineCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (Utils.getApplicationByReflect()?.isConnected() == true) {
                val build = CacheControl.Builder()
                    .maxAge(20, TimeUnit.SECONDS)
                    .build()
                request = request.newBuilder()
                    .cacheControl(build)
                    .build()
            }
            return@Interceptor chain.proceed(request)
        }

    }

    /**
     * 有网络时在限定时间内多次请求取缓存，超过时间重新请求：
     *
     * @return 返回缓存拦截器
     */
    private fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val proceed = chain.proceed(chain.request())
            // 正常访问同一请求接口（多次访问同一接口），给30秒缓存，超过时间重新发送请求，否则取缓存数据
            val build = CacheControl.Builder()
                .maxAge(30, TimeUnit.SECONDS)
                .build()
            return@Interceptor proceed.newBuilder()
                .header("cache_control", build.toString())
                .build()
        }
    }
}