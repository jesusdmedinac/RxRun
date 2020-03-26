package com.mupper.reactiveelements

import com.mupper.reactiveelements.util.TestClass
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Completable
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ReactiveElementsAnyToReactiveElementTest {
    @Test
    fun `toObservable should return an observable with just expected string`() {
        // Given
        val expectedString = "Some string"

        // When
        val observableTest = expectedString.toObservable().test()

        // Then
        observableTest.assertValue(expectedString)
    }

    @Test
    fun `toSingle should return a single with just expected string`() {
        // Given
        val expectedString = "Some string"

        // When
        val singleTest = expectedString.toSingle().test()

        // Then
        singleTest.assertValue(expectedString)
    }

    @Test
    fun `toFlowable should return a flowable with just expected string`() {
        // Given
        val expectedString = "Some string"

        // When
        val flowableTest = expectedString.toFlowable().test()

        // Then
        flowableTest.assertValue(expectedString)
    }

    @Test
    fun `toCompletable should return a completable from expected action`() {
        // Given
        val expectedAction = {}

        // When
        val completable = expectedAction.toCompletable()

        // Then
        assertThat(completable, instanceOf(Completable::class.java))
    }

    @Test
    fun `subscribe should call someFunction of TestClass given completable from toCompletable`() {
        // Given
        val spyTest: TestClass = spyk()

        val expectedAction = {
            spyTest.someFunction()
        }

        val completable = expectedAction.toCompletable()

        // When
        completable.subscribe()

        // Then
        verify { spyTest.someFunction() }
    }
}