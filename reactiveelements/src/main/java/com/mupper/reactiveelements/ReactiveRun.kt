package com.mupper.reactiveelements

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

// Run on io
fun <T> Observable<T>.runOnIo(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(onNext, onError)

fun <T> Single<T>.runOnIo(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(onNext, onError)

fun <T> Flowable<T>.runOnIo(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(onNext, onError)

fun Completable.runOnIo(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: () -> Unit = {}
): Disposable =
    subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(onNext, onError)

// Run on main
fun <T> Observable<T>.runOnMain(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(onNext, onError)

fun <T> Single<T>.runOnMain(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(onNext, onError)

fun <T> Flowable<T>.runOnMain(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: (t: T) -> Unit = {}
): Disposable =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(onNext, onError)

fun Completable.runOnMain(
    onError: (t: Throwable) -> Unit = Throwable::printStackTrace,
    onNext: () -> Unit = {}
): Disposable =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(onNext, onError)

/**
 * time to await on blocking get calls
 */
private const val AWAIT_UP_TO: Long = 15

// Get on io
fun <T> Observable<T>.getOnIo(): T =
    subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).blockingFirst()

fun <T> Single<T>.getOnIo(): T =
    subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).blockingGet()

fun <T> Flowable<T>.getOnIo(): T =
    subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).blockingFirst()

fun Completable.getOnIo(timeToAwait: Long = AWAIT_UP_TO): Boolean {
    return subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
        .blockingAwait(timeToAwait, TimeUnit.SECONDS)
}

// Get on Main
fun <T> Observable<T>.getOnMain(): T =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).blockingFirst()

fun <T> Single<T>.getOnMain(): T =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).blockingGet()

fun <T> Flowable<T>.getOnMain(): T =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).blockingFirst()

fun Completable.getOnMain(timeToAwait: Long = AWAIT_UP_TO): Boolean =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .blockingAwait(timeToAwait, TimeUnit.SECONDS)

// Only subscribe and observe on io
inline fun <T> Observable<T>.onIo(block: Observable<T>.() -> Observable<T> = { this }): Observable<T> =
    block().subscribeOn(Schedulers.io()).observeOn(Schedulers.io())

inline fun <T> Single<T>.onIo(block: Single<T>.() -> Single<T> = { this }): Single<T> =
    block().subscribeOn(Schedulers.io()).observeOn(Schedulers.io())

inline fun <T> Flowable<T>.onIo(block: Flowable<T>.() -> Flowable<T> = { this }): Flowable<T> =
    block().subscribeOn(Schedulers.io()).observeOn(Schedulers.io())

inline fun Completable.onIo(block: Completable.() -> Completable = { this }): Completable =
    block().subscribeOn(Schedulers.io()).observeOn(Schedulers.io())

