package com.davidmendozamartinez.coroutines.sample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(coroutinesTestRule.testDispatcher)
    }

    @Test
    fun `success if username and password are not empty`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val observer = mock<Observer<Boolean>>()
            viewModel.onSubmitClicked("username", "password").observeForever(observer)
            verify(observer).onChanged(true)
        }

    @Test
    fun `error if username is empty`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val observer = mock<Observer<Boolean>>()
        viewModel.onSubmitClicked("", "password").observeForever(observer)
        verify(observer).onChanged(false)
    }

    @Test
    fun `error if password is empty`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val observer = mock<Observer<Boolean>>()
        viewModel.onSubmitClicked("username", "").observeForever(observer)
        verify(observer).onChanged(false)
    }

    @Test
    fun `error if username and password are empty`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val observer = mock<Observer<Boolean>>()
            viewModel.onSubmitClicked("", "").observeForever(observer)
            verify(observer).onChanged(false)
        }
}