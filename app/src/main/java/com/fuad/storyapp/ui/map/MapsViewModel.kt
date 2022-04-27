package com.fuad.storyapp.ui.map

import androidx.lifecycle.ViewModel
import com.fuad.storyapp.data.repository.StoryRepository

class MapsViewModel(private val storyRepo: StoryRepository) : ViewModel() {
    fun getStories(token: String) = storyRepo.getStoriesLocation(token)
}