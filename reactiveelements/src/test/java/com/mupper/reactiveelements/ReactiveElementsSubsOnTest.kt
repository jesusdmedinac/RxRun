package com.mupper.reactiveelements

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class ReactiveElementsSubsOnTest {
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

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `subsOn should call subscribeOn with IO scheduler on Observable element`() {
        // Given

        // When
        spyObservable subsOn io()

        // Then
        verify {
            spyObservable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `subsOn should call subscribeOn with IO scheduler on single element`() {
        // Given

        // When
        spySingle subsOn io()

        // Then
        verify {
            spySingle.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `subsOn should call subscribeOn with IO scheduler on flowable element`() {
        // Given

        // When
        spyFlowable subsOn io()

        // Then
        verify {
            spyFlowable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `subsOn should call subscribeOn with IO scheduler on completable element`() {
        // Given

        // When
        spyCompletable subsOn io()

        // Then
        verify {
            spyCompletable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `subsOn should call subscribeOn with Computation scheduler on Observable element`() {
        // Given

        // When
        spyObservable subsOn computation()

        // Then
        verify {
            spyObservable.subscribeOn(Schedulers.computation())
        }
    }

    @Test
    fun `subsOn should call subscribeOn with Computation scheduler on single element`() {
        // Given

        // When
        spySingle subsOn computation()

        // Then
        verify {
            spySingle.subscribeOn(Schedulers.computation())
        }
    }

    @Test
    fun `subsOn should call subscribeOn with Computation scheduler on flowable element`() {
        // Given

        // When
        spyFlowable subsOn computation()

        // Then
        verify {
            spyFlowable.subscribeOn(Schedulers.computation())
        }
    }

    @Test
    fun `subsOn should call subscribeOn with Computation scheduler on completable element`() {
        // Given

        // When
        spyCompletable subsOn computation()

        // Then
        verify {
            spyCompletable.subscribeOn(Schedulers.computation())
        }
    }

    @Test
    fun `subsOn should call subscribeOn with new thread scheduler on Observable element`() {
        // Given

        // When
        spyObservable subsOn newThread()

        // Then
        verify {
            spyObservable.subscribeOn(Schedulers.newThread())
        }
    }

    @Test
    fun `subsOn should call subscribeOn with new thread scheduler on single element`() {
        // Given

        // When
        spySingle subsOn newThread()

        // Then
        verify {
            spySingle.subscribeOn(Schedulers.newThread())
        }
    }

    @Test
    fun `subsOn should call subscribeOn with new thread scheduler on flowable element`() {
        // Given

        // When
        spyFlowable subsOn newThread()

        // Then
        verify {
            spyFlowable.subscribeOn(Schedulers.newThread())
        }
    }

    @Test
    fun `subsOn should call subscribeOn with new thread scheduler on completable element`() {
        // Given

        // When
        spyCompletable subsOn newThread()

        // Then
        verify {
            spyCompletable.subscribeOn(Schedulers.newThread())
        }
    }
}
