package ru.codeoverflow.openspaceapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.codeoverflow.openspaceapp.BuildConfig.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.codeoverflow.openspaceapp.BuildConfig
import ru.codeoverflow.openspaceapp.model.server.OpenSpaceApi
import ru.codeoverflow.openspaceapp.model.storage.Prefs
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { provideDefaultOkhttpClient(androidContext(), get()) }
    single { provideOpenSpaceApi(provideRetrofit(androidContext(), BASE_URL, get())) }
}

fun provideDefaultOkhttpClient(contex: Context, prefs: Prefs): OkHttpClient.Builder {
    return OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(contex))
        .addInterceptor {
            val newBuilder = it.request().newBuilder()
            prefs.token?.let { token ->
                newBuilder
                    .addHeader("Authorization", "Bearer $token")
            }
            it.proceed(newBuilder.build())
        }
        .readTimeout(90, TimeUnit.SECONDS)
        .connectTimeout(90, TimeUnit.SECONDS)
}

fun provideRetrofit(contex: Context, baseUrl: String, prefs: Prefs): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(provideDefaultOkhttpClient(contex, prefs).build())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOpenSpaceApi(retrofit: Retrofit): OpenSpaceApi =
    retrofit.create(OpenSpaceApi::class.java)