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
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

/**
 * Created by jesus.medina on 12/2019.
 * Insulet Corporation
 * Andromeda
 */
class ReactiveElementsGetOnMainTest {
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
    fun `getOnMain should call subscribeOn with IO scheduler on observable element`() {
        // Given

        // When
        spyObservable.getOnMain()

        // Then
        verify {
            spyObservable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `getOnMain should call observeOn with IO scheduler on observable element`() {
        // Given
        every { spyObservable.subscribeOn(Schedulers.io()) } returns spyObservable

        // When
        spyObservable.getOnMain()

        // Then
        verify {
            spyObservable.observeOn(AndroidSchedulers.mainThread())
        }
    }

    @Test
    fun `getOnMain should call subscribeOn with IO scheduler on single element`() {
        // Given

        // When
        spySingle.getOnMain()

        // Then
        verify {
            spySingle.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `getOnMain should call observeOn with IO scheduler on single element`() {
        // Given
        every { spySingle.subscribeOn(Schedulers.io()) } returns spySingle

        // When
        spySingle.getOnMain()

        // Then
        verify {
            spySingle.observeOn(AndroidSchedulers.mainThread())
        }
    }

    @Test
    fun `getOnMain should call subscribeOn with IO scheduler on flowable element`() {
        // Given

        // When
        spyFlowable.getOnMain()

        // Then
        verify {
            spyFlowable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `getOnMain should call observeOn with IO scheduler on flowable element`() {
        // Given
        every { spyFlowable.subscribeOn(Schedulers.io()) } returns spyFlowable

        // When
        spyFlowable.getOnMain()

        // Then
        verify {
            spyFlowable.observeOn(AndroidSchedulers.mainThread())
        }
    }

    @Test
    fun `getOnMain should call subscribeOn with IO scheduler on completable element`() {
        // Given

        // When
        spyCompletable.getOnMain()

        // Then
        verify {
            spyCompletable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `getOnMain should call observeOn with IO scheduler on completable element`() {
        // Given
        every { spyCompletable.subscribeOn(Schedulers.io()) } returns spyCompletable

        // When
        spyCompletable.getOnMain()

        // Then
        verify {
            spyCompletable.observeOn(AndroidSchedulers.mainThread())
        }
    }
}
