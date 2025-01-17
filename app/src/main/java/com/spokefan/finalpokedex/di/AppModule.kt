package com.spokefan.finalpokedex.di

import com.spokefan.finalpokedex.data.remote.PokeApi
import com.spokefan.finalpokedex.data.repository.PokemonRepositoryImpl
import com.spokefan.finalpokedex.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providePokemonRepository(api: PokeApi) = PokemonRepositoryImpl(api)

    @Singleton
    @Provides
    fun providePokeApi(): PokeApi {
        val okHttpClient = OkHttpClient()

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val clientWith60sTimeout = okHttpClient.newBuilder()
            .addInterceptor(interceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientWith60sTimeout)
            .build()
            .create(PokeApi::class.java)
    }
}