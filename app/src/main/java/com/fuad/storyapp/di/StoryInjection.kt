package com.fuad.storyapp.di

import com.fuad.storyapp.data.repository.StoryRepository
import com.fuad.storyapp.data.retrofit.ApiConfig

object StoryInjection {
    fun provideRepository(): StoryRepository {
        val apiService = ApiConfig.getApiService()
        return StoryRepository.getInstance(apiService)
    }
}