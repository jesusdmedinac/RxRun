package com.mupper.reactiveelements

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

/**
 * Created by jesus.medina on 12/2019.
 * Insulet Corporation
 * Andromeda
 */
class ReactiveElementsRunOnMainTest {
    @SpyK
    var spyObservable = Observable.just(Unit)

    @SpyK
    var spySingle = Single.just(Unit)

    @SpyK
    var spyFlowable = Flowable.just(Unit)

    @SpyK
    var spyCompletable = Completable.complete()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `runOnIo should call subscribeOn with main scheduler on observable element`() {
        // Given

        // When
        spyObservable.runOnMain()

        // Then
        verify {
            spyObservable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `runOnIo should call observeOn with main scheduler on observable element`() {
        // Given
        every { spyObservable.subscribeOn(Schedulers.io()) } returns spyObservable

        // When
        spyObservable.runOnMain()

        // Then
        verify {
            spyObservable.observeOn (AndroidSchedulers.mainThread())
        }
    }

    @Test
    fun `runOnIo should call subscribeOn with main scheduler on single element`() {
        // Given

        // When
        spySingle.runOnMain()

        // Then
        verify {
            spySingle.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `runOnIo should call observeOn with main scheduler on single element`() {
        // Given
        every { spySingle.subscribeOn(Schedulers.io()) } returns spySingle

        // When
        spySingle.runOnMain()

        // Then
        verify {
            spySingle.observeOn (AndroidSchedulers.mainThread())
        }
    }

    @Test
    fun `runOnIo should call subscribeOn with main scheduler on flowable element`() {
        // Given

        // When
        spyFlowable.runOnMain()

        // Then
        verify {
            spyFlowable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `runOnIo should call observeOn with main scheduler on flowable element`() {
        // Given
        every { spyFlowable.subscribeOn(Schedulers.io()) } returns spyFlowable

        // When
        spyFlowable.runOnMain()

        // Then
        verify {
            spyFlowable.observeOn (AndroidSchedulers.mainThread())
        }
    }

    @Test
    fun `runOnIo should call subscribeOn with main scheduler on completable element`() {
        // Given

        // When
        spyCompletable.runOnMain()

        // Then
        verify {
            spyCompletable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `runOnIo should call observeOn with main scheduler on completable element`() {
        // Given
        every { spyCompletable.subscribeOn(Schedulers.io()) } returns spyCompletable

        // When
        spyCompletable.runOnMain()

        // Then
        verify {
            spyCompletable.observeOn (AndroidSchedulers.mainThread())
        }
    }
}
