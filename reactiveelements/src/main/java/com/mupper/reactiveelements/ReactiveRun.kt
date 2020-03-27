package com.mupper.reactiveelements

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

// Run on io
fun <T> Observable<T>.runOnIo(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    onIo { this } .subscribe(onNext, onError)

fun <T> Single<T>.runOnIo(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    onIo { this } .subscribe(onNext, onError)

fun <T> Flowable<T>.runOnIo(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    onIo { this } .subscribe(onNext, onError)

fun Completable.runOnIo(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: () -> Unit = {}
): Disposable =
    onIo { this } .subscribe(onNext, onError)

// Run a lambda action on Io
fun (() -> Unit).runOnIo(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: () -> Unit = {}
) = Completable.fromAction(this).runOnIo(onError, onNext)

// Run on main
fun <T> Observable<T>.runOnMain(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    onMain { this }.subscribe(onNext, onError)

fun <T> Single<T>.runOnMain(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    onMain { this }.subscribe(onNext, onError)

fun <T> Flowable<T>.runOnMain(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    onMain { this }.subscribe(onNext, onError)

fun Completable.runOnMain(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: () -> Unit = {}
): Disposable =
    onMain { this }.subscribe(onNext, onError)

// Run a lambda action on Main
fun (() -> Unit).runOnMain(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: () -> Unit = {}
) = Completable.fromAction(this).runOnMain(onError, onNext)

/**
 * time to await on blocking get calls
 */
private const val AWAIT_UP_TO: Long = 15

// Get on io
fun <T> Observable<T>.getOnIo(): T =
    onIo { this } .blockingFirst()

fun <T> Single<T>.getOnIo(): T =
    onIo { this } .blockingGet()

fun <T> Flowable<T>.getOnIo(): T =
    onIo { this } .blockingFirst()

fun Completable.getOnIo(timeToAwait: Long = AWAIT_UP_TO): Boolean =
    onIo { this } .blockingAwait(timeToAwait, TimeUnit.SECONDS)

// Get on Main
fun <T> Observable<T>.getOnMain(): T =
    onMain { this }.blockingFirst()

fun <T> Single<T>.getOnMain(): T =
    onMain { this }.blockingGet()

fun <T> Flowable<T>.getOnMain(): T =
    onMain { this }.blockingFirst()

fun Completable.getOnMain(timeToAwait: Long = AWAIT_UP_TO): Boolean =
    onMain { this }.blockingAwait(timeToAwait, TimeUnit.SECONDS)


