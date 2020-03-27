package com.mupper.reactiveelements

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import io.reactivex.Flowable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.reactivestreams.Publisher

class LiveDataToReactiveStreamsTest {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @SpyK
    var spyFlowable = Flowable.just(Unit)

    @SpyK
    var spyLiveData = MutableLiveData(Unit)

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `toLiveData should convert a flowable to LiveData of same type`() {
        // Given

        // When
        val liveData = spyFlowable.toLiveData()

        // Then
        assertThat(liveData, instanceOf(LiveData::class.java))
    }

    @Test
    fun `toPublisher should convert LiveData to flowable of same type`() {
        // Given
        val mockLifecycleOwner: LifecycleOwner = mockk()

        // When
        val publisher = spyLiveData.toPublisher(mockLifecycleOwner)

        // Then
        assertThat(publisher, instanceOf(Publisher::class.java))
    }
}