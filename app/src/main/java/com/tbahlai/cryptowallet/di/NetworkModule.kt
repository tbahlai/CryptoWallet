package com.tbahlai.cryptowallet.di

import com.google.gson.GsonBuilder
import com.tbahlai.cryptowallet.BuildConfig
import com.tbahlai.cryptowallet.data.BaseApiService
import com.tbahlai.cryptowallet.data.adapters.DateAdapter
import com.tbahlai.cryptowallet.data.adapters.RequestResultAdapterFactory
import com.tbahlai.cryptowallet.data.interceptors.ErrorsHandlerInterceptor
import com.tbahlai.cryptowallet.data.interceptors.HeadersInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Instant
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        val gson = GsonBuilder().registerTypeAdapter(Instant::class.java, DateAdapter()).create()
        return Retrofit.Builder()
            .addCallAdapterFactory(RequestResultAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideBaseApi(
        okHttpClient: OkHttpClient,
        retrofitBuilder: Retrofit.Builder,
    ): BaseApiService {
        val retrofit = retrofitBuilder
            .baseUrl(BuildConfig.BASE_API_URL)
            .callFactory(okHttpClient)
            .build()
        return retrofit.create(BaseApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @Named(INTERCEPTOR_LOGGING) loggingInterceptor: Interceptor,
        @Named(INTERCEPTOR_HEADER) headersInterceptor: Interceptor,
        @Named(INTERCEPTOR_ERROR_HANDLER) errorsHandlerInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headersInterceptor)
            .addInterceptor(errorsHandlerInterceptor)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Named(INTERCEPTOR_LOGGING)
    fun provideLoggingInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = if (BuildConfig.DEBUG) Level.BODY else Level.BASIC
        }
        return httpLoggingInterceptor
    }

    @Provides
    @Named(INTERCEPTOR_HEADER)
    fun provideHeadersInterceptor(): Interceptor {
        return HeadersInterceptor()
    }

    @Provides
    @Named(INTERCEPTOR_ERROR_HANDLER)
    fun provideErrorsHandlerInterceptor(): Interceptor {
        return ErrorsHandlerInterceptor()
    }

    companion object {
        private const val INTERCEPTOR_LOGGING = "interceptor_logging"
        private const val INTERCEPTOR_HEADER = "interceptor_header"
        private const val INTERCEPTOR_ERROR_HANDLER = "interceptor_error_handler"
        private const val DEFAULT_TIMEOUT = 20L
    }
}
