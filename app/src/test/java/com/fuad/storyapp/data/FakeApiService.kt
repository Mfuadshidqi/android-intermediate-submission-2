package com.fuad.storyapp.data

import com.fuad.storyapp.DataDummy
import com.fuad.storyapp.data.remote.response.LoginResponse
import com.fuad.storyapp.data.remote.response.RegisterResponse
import com.fuad.storyapp.data.remote.response.StoriesResponse
import com.fuad.storyapp.data.remote.response.UploadStoryResponse
import com.fuad.storyapp.data.remote.retrofit.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody

class FakeApiService : ApiService {
    private val dummyLoginResponse = DataDummy.generateDummyLoginResponse()
    private val dummySignupResponse = DataDummy.generateDummyRegisterResponse()
    private val dummyStories = DataDummy.generateDummyStoriesResponse()
    private val dummyUploadStory = DataDummy.generateDummyUploadStoryResponse()

    override suspend fun register(name: String, email: String, password: String): RegisterResponse {
        return dummySignupResponse
    }

    override suspend fun login(email: String, password: String): LoginResponse {
        return dummyLoginResponse
    }

    override suspend fun getStories(
        token: String,
        page: Int?,
        size: Int?,
        location: Int
    ): StoriesResponse {
        return dummyStories
    }

    override suspend fun uploadStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody,
        lat: RequestBody?,
        lon: RequestBody?
    ): UploadStoryResponse {
        return dummyUploadStory
    }
}