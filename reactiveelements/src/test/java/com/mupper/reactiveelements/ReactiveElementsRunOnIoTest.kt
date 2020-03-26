package com.mupper.reactiveelements

import com.mupper.reactiveelements.util.RxImmediateSchedulerRule
import com.mupper.reactiveelements.util.TestClass
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception
import java.lang.RuntimeException

/**
 * Created by jesus.medina on 12/2019.
 * Insulet Corporation
 * Andromeda
 */
class ReactiveElementsRunOnIoTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

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
    fun `runOnIo should call subscribeOn with IO scheduler on observable element`() {
        // Given

        // When
        spyObservable.runOnIo()

        // Then
        verify {
            spyObservable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `runOnIo should call observeOn with IO scheduler on observable element`() {
        // Given
        every { spyObservable.subscribeOn(Schedulers.io()) } returns spyObservable

        // When
        spyObservable.runOnIo()

        // Then
        verify {
            spyObservable.observeOn(Schedulers.io())
        }
    }

    @Test
    fun `runOnIo should call subscribeOn with IO scheduler on single element`() {
        // Given

        // When
        spySingle.runOnIo()

        // Then
        verify {
            spySingle.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `runOnIo should call observeOn with IO scheduler on single element`() {
        // Given
        every { spySingle.subscribeOn(Schedulers.io()) } returns spySingle

        // When
        spySingle.runOnIo()

        // Then
        verify {
            spySingle.observeOn(Schedulers.io())
        }
    }

    @Test
    fun `runOnIo should call subscribeOn with IO scheduler on flowable element`() {
        // Given

        // When
        spyFlowable.runOnIo()

        // Then
        verify {
            spyFlowable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `runOnIo should call observeOn with IO scheduler on flowable element`() {
        // Given
        every { spyFlowable.subscribeOn(Schedulers.io()) } returns spyFlowable

        // When
        spyFlowable.runOnIo()

        // Then
        verify {
            spyFlowable.observeOn(Schedulers.io())
        }
    }

    @Test
    fun `runOnIo should call subscribeOn with IO scheduler on completable element`() {
        // Given

        // When
        spyCompletable.runOnIo()

        // Then
        verify {
            spyCompletable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `runOnIo should call observeOn with IO scheduler on completable element`() {
        // Given
        every { spyCompletable.subscribeOn(Schedulers.io()) } returns spyCompletable

        // When
        spyCompletable.runOnIo()

        // Then
        verify {
            spyCompletable.observeOn(Schedulers.io())
        }
    }

    @Test
    fun `runOnIo should call someFunction of TestClass on observable element`() {
        // Given
        val spyTest: TestClass = spyk()

        // When
        spyObservable.runOnIo {
            spyTest.someFunction()
        }

        // Then
        verify { spyTest.someFunction() }
    }

    @Test
    fun `runOnIo should call someFunction of TestClass in onError function when some Exception is thrown on observable element`() {
        // Given
        val spyTest: TestClass = spyk()

        // When
        spyObservable.runOnIo({
            spyTest.someFunction()
        }) {
            throw Exception()
        }

        // Then
        verify { spyTest.someFunction() }
    }

    @Test
    fun `runOnIo should call someFunction of TestClass on single element`() {
        // Given
        val spyTest: TestClass = spyk()

        // When
        spySingle.runOnIo {
            spyTest.someFunction()
        }

        // Then
        verify { spyTest.someFunction() }
    }

    @Test
    fun `runOnIo should catch RuntimeException in onError function when RuntimeException is thrown on single element`() {
        // Given spySingle

        // When
        spySingle.runOnIo({
            assertThat(it, instanceOf(RuntimeException::class.java))
        }) {
            throw RuntimeException()
        }
    }

    @Test
    fun `runOnIo should call someFunction of TestClass on flowable element`() {
        // Given
        val spyTest: TestClass = spyk()

        // When
        spyFlowable.runOnIo {
            spyTest.someFunction()
        }

        // Then
        verify { spyTest.someFunction() }
    }

    @Test
    fun `runOnIo should call someFunction of TestClass in onError function when some Exception is thrown on flowable element`() {
        // Given
        val spyTest: TestClass = spyk()

        // When
        spyFlowable.runOnIo({
            spyTest.someFunction()
        }) {
            throw Exception()
        }

        // Then
        verify { spyTest.someFunction() }
    }

    @Test
    fun `runOnIo should call someFunction of TestClass on completable element`() {
        // Given
        val spyTest: TestClass = spyk()

        // When
        spyCompletable.runOnIo {
            spyTest.someFunction()
        }

        // Then
        verify { spyTest.someFunction() }
    }

    @Test
    fun `runOnIo should catch RuntimeException in onError function when RuntimeException is thrown on completable element`() {
        // Given spySingle

        // When
        spyCompletable.runOnIo({
            assertThat(it, instanceOf(RuntimeException::class.java))
        }) {
            throw RuntimeException()
        }
    }

    @Test
    fun `runOnIo should call someFunction of TestClass on lambda action`() {
        // Given
        val spyTest: TestClass = spyk()

        // When
        val lambdaAction = {}
        lambdaAction.runOnIo {
            spyTest.someFunction()
        }

        // Then
        verify { spyTest.someFunction() }
    }

    @Test
    fun `lambdaAction should call someFunction of TestClass when runOnIo is called on lambda action`() {
        // Given
        val spyTest: TestClass = spyk()
        val lambdaAction = {
            spyTest.someFunction()
        }

        // When
        lambdaAction.runOnIo()

        // Then
        verify { spyTest.someFunction() }
    }
}
