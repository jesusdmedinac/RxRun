package com.mupper.reactiveelements

import androidx.lifecycle.LiveData
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.SpyK
import io.reactivex.Flowable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class LiveDataToReactiveStreamsTest {

    @SpyK
    var spyFlowable = Flowable.just(Unit)

    @SpyK
    var spyLiveData = Flowable.just(Unit)

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

        // When
        val flowable = spyLiveData.toFlowable()

        // Then
        assertThat(flowable, instanceOf(Flowable::class.java))
    }
}