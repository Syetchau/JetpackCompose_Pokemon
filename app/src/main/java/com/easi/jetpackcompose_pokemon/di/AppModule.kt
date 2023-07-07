package com.easi.jetpackcompose_pokemon.di

import com.easi.jetpackcompose_pokemon.api.Api
import com.easi.jetpackcompose_pokemon.constants.Constants.BASE_URL
import com.easi.jetpackcompose_pokemon.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonRepository(api: Api) = PokemonRepository(api = api)

    @Singleton
    @Provides
    fun provideApi(): Api {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(Api::class.java)
    }
}