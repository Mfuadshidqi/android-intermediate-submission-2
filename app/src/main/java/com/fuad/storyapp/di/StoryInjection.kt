package com.fuad.storyapp.di

import android.content.Context
import com.fuad.storyapp.data.local.room.StoryDatabase
import com.fuad.storyapp.data.repository.StoryRepository
import com.fuad.storyapp.data.remote.retrofit.ApiConfig

object StoryInjection {
    fun provideRepository(context: Context): StoryRepository {
        val apiService = ApiConfig.getApiService()
        val database = StoryDatabase.getDatabase(context)
        return StoryRepository(apiService,database)
    }
}