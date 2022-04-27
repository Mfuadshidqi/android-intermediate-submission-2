package com.fuad.storyapp.ui.signup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.fuad.storyapp.DataDummy
import com.fuad.storyapp.data.remote.response.RegisterResponse
import com.fuad.storyapp.data.repository.UserRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import com.fuad.storyapp.data.Result
import com.fuad.storyapp.getOrAwaitValue

@RunWith(MockitoJUnitRunner::class)
class SignupViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRepository: UserRepository
    private lateinit var registerViewModel: SignupViewModel
    private val dummyRegisterResponse = DataDummy.generateDummyRegisterResponse()
    private val dummyName = "User"
    private val dummyEmail = "user@mail.com"
    private val dummyPassword = "password"

    @Before
    fun setup() {
        registerViewModel = SignupViewModel(userRepository)
    }

    @Test
    fun `when signup success and Result Success`() {
        val expectedRegisteResponse = MutableLiveData<Result<RegisterResponse>>()
        expectedRegisteResponse.value = Result.Success(dummyRegisterResponse)

        Mockito.`when`(registerViewModel.register(dummyName, dummyEmail, dummyPassword)).thenReturn(expectedRegisteResponse)

        val actualRegisterResponse = registerViewModel.register(dummyName, dummyEmail, dummyPassword).getOrAwaitValue()
        Mockito.verify(userRepository).register(dummyName, dummyEmail, dummyPassword)
        Assert.assertNotNull(actualRegisterResponse)
        Assert.assertTrue(actualRegisterResponse is Result.Success)
        Assert.assertSame(dummyRegisterResponse, (actualRegisterResponse as Result.Success).data)
    }

    @Test
    fun `when signup failed and Result Error`() {
        val signupResponse = MutableLiveData<Result<RegisterResponse>>()
        signupResponse.value = Result.Error("Error")

        Mockito.`when`(registerViewModel.register(dummyName, dummyEmail, dummyPassword)).thenReturn(signupResponse)

        val actualSignupResponse = registerViewModel.register(dummyName, dummyEmail, dummyPassword).getOrAwaitValue()
        Mockito.verify(userRepository).register(dummyName, dummyEmail, dummyPassword)
        Assert.assertNotNull(actualSignupResponse)
        Assert.assertTrue(actualSignupResponse is Result.Error)
    }
}