package com.fuad.storyapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.*
import com.fuad.storyapp.data.remote.response.StoriesResponse
import com.fuad.storyapp.data.remote.retrofit.ApiService
import java.lang.Exception
import com.fuad.storyapp.data.Result
import com.fuad.storyapp.data.StoryRemoteMediator
import com.fuad.storyapp.data.local.entity.Story
import com.fuad.storyapp.data.local.room.StoryDatabase
import com.fuad.storyapp.data.remote.response.UploadStoryResponse
import com.fuad.storyapp.utils.wrapEspressoIdlingResource
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StoryRepository(private val apiService: ApiService,private val storyDatabase: StoryDatabase) {

    fun getStories(token: String): LiveData<PagingData<Story>>{
        wrapEspressoIdlingResource {
            @OptIn(ExperimentalPagingApi::class)
            return Pager(
                config = PagingConfig(
                    pageSize = 5
                ),
                remoteMediator = StoryRemoteMediator(storyDatabase, apiService, token),
                pagingSourceFactory = {
                    storyDatabase.storyDao().getAllStories()
                }
            ).liveData
        }
    }

    fun getStoriesLocation(token: String) : LiveData<Result<StoriesResponse>> = liveData{
        emit(Result.Loading)
        try {
            val client = apiService.getStories("Bearer $token", location = 1)
            emit(Result.Success(client))
        }catch (e : Exception){
            emit(Result.Error(e.message.toString()))
        }
    }

    fun uploadStory(token: String, imageMultipart: MultipartBody.Part, desc: RequestBody, lat: RequestBody?, lon: RequestBody?):
            LiveData<Result<UploadStoryResponse>> = liveData{
        emit(Result.Loading)
        try {
            val client = apiService.uploadStory("Bearer $token",imageMultipart, desc, lat, lon)
            emit(Result.Success(client))
        }catch (e : Exception){
            emit(Result.Error(e.message.toString()))
        }
    }

}