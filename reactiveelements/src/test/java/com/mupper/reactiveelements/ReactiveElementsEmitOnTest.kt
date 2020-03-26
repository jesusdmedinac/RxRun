package com.mupper.reactiveelements

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class ReactiveElementsEmitOnTest {
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
    fun `emitOn should call observeOn with IO scheduler on Observable element`() {
        // Given

        // When
        spyObservable emitOn io

        // Then
        verify {
            spyObservable.observeOn(Schedulers.io())
        }
    }

    @Test
    fun `emitOn should call observeOn with IO scheduler on single element`() {
        // Given

        // When
        spySingle emitOn io

        // Then
        verify {
            spySingle.observeOn(Schedulers.io())
        }
    }

    @Test
    fun `emitOn should call observeOn with IO scheduler on flowable element`() {
        // Given

        // When
        spyFlowable emitOn io

        // Then
        verify {
            spyFlowable.observeOn(Schedulers.io())
        }
    }

    @Test
    fun `emitOn should call observeOn with IO scheduler on completable element`() {
        // Given

        // When
        spyCompletable emitOn io

        // Then
        verify {
            spyCompletable.observeOn(Schedulers.io())
        }
    }

    @Test
    fun `emitOn should call observeOn with Computation scheduler on Observable element`() {
        // Given

        // When
        spyObservable emitOn computation

        // Then
        verify {
            spyObservable.observeOn(Schedulers.computation())
        }
    }

    @Test
    fun `emitOn should call observeOn with Computation scheduler on single element`() {
        // Given

        // When
        spySingle emitOn computation

        // Then
        verify {
            spySingle.observeOn(Schedulers.computation())
        }
    }

    @Test
    fun `emitOn should call observeOn with Computation scheduler on flowable element`() {
        // Given

        // When
        spyFlowable emitOn computation

        // Then
        verify {
            spyFlowable.observeOn(Schedulers.computation())
        }
    }

    @Test
    fun `emitOn should call observeOn with Computation scheduler on completable element`() {
        // Given

        // When
        spyCompletable emitOn computation

        // Then
        verify {
            spyCompletable.observeOn(Schedulers.computation())
        }
    }

    @Test
    fun `emitOn should call observeOn with new thread scheduler on Observable element`() {
        // Given

        // When
        spyObservable emitOn newThread

        // Then
        verify {
            spyObservable.observeOn(Schedulers.newThread())
        }
    }

    @Test
    fun `emitOn should call observeOn with new thread scheduler on single element`() {
        // Given

        // When
        spySingle emitOn newThread

        // Then
        verify {
            spySingle.observeOn(Schedulers.newThread())
        }
    }

    @Test
    fun `emitOn should call observeOn with new thread scheduler on flowable element`() {
        // Given

        // When
        spyFlowable emitOn newThread

        // Then
        verify {
            spyFlowable.observeOn(Schedulers.newThread())
        }
    }

    @Test
    fun `emitOn should call observeOn with new thread scheduler on completable element`() {
        // Given

        // When
        spyCompletable emitOn newThread

        // Then
        verify {
            spyCompletable.observeOn(Schedulers.newThread())
        }
    }
}
