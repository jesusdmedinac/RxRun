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
    onIo().subscribe(onNext, onError)

fun <T> Single<T>.runOnIo(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    onIo().subscribe(onNext, onError)

fun <T> Flowable<T>.runOnIo(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    onIo().subscribe(onNext, onError)

fun Completable.runOnIo(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: () -> Unit = {}
): Disposable =
    onIo().subscribe(onNext, onError)

// Run any on Io
fun (() -> Unit).runOnIo(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: () -> Unit = {}
) = Completable.fromAction(this).runOnIo(onError, onNext)

// Run on main
fun <T> Observable<T>.runOnMain(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    onMain().subscribe(onNext, onError)

fun <T> Single<T>.runOnMain(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    onMain().subscribe(onNext, onError)

fun <T> Flowable<T>.runOnMain(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    onMain().subscribe(onNext, onError)

fun Completable.runOnMain(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: () -> Unit = {}
): Disposable =
    onMain().subscribe(onNext, onError)

/**
 * time to await on blocking get calls
 */
private const val AWAIT_UP_TO: Long = 15

// Get on io
fun <T> Observable<T>.getOnIo(): T =
    onIo().blockingFirst()

fun <T> Single<T>.getOnIo(): T =
    onIo().blockingGet()

fun <T> Flowable<T>.getOnIo(): T =
    onIo().blockingFirst()

fun Completable.getOnIo(timeToAwait: Long = AWAIT_UP_TO): Boolean =
    onIo().blockingAwait(timeToAwait, TimeUnit.SECONDS)

// Get on Main
fun <T> Observable<T>.getOnMain(): T =
    onMain().blockingFirst()

fun <T> Single<T>.getOnMain(): T =
    onMain().blockingGet()

fun <T> Flowable<T>.getOnMain(): T =
    onMain().blockingFirst()

fun Completable.getOnMain(timeToAwait: Long = AWAIT_UP_TO): Boolean =
    onMain().blockingAwait(timeToAwait, TimeUnit.SECONDS)

// Only subscribe and observe on io
inline fun <T> Observable<T>.onIo(block: Observable<T>.() -> Observable<T> = { this }): Observable<T> =
    (block() subsOn io()) emitOn io()

inline fun <T> Single<T>.onIo(block: Single<T>.() -> Single<T> = { this }): Single<T> =
    (block() subsOn io()) emitOn io()

inline fun <T> Flowable<T>.onIo(block: Flowable<T>.() -> Flowable<T> = { this }): Flowable<T> =
    (block() subsOn io()) emitOn io()

inline fun Completable.onIo(block: Completable.() -> Completable = { this }): Completable =
    (block() subsOn io()) emitOn io()

// Only subscribe on io and observe on main
inline fun <T> Observable<T>.onMain(block: Observable<T>.() -> Observable<T> = { this }): Observable<T> =
    (block() subsOn io()) emitOn mainThread()

inline fun <T> Single<T>.onMain(block: Single<T>.() -> Single<T> = { this }): Single<T> =
    (block() subsOn io()) emitOn mainThread()

inline fun <T> Flowable<T>.onMain(block: Flowable<T>.() -> Flowable<T> = { this }): Flowable<T> =
    (block() subsOn io()) emitOn mainThread()

inline fun Completable.onMain(block: Completable.() -> Completable = { this }): Completable =
    (block() subsOn io()) emitOn mainThread()

