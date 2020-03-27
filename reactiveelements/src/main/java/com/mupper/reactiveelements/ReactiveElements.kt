package com.mupper.reactiveelements

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.Flowable
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> T.toObservable() = Observable.just(this)

fun <T> T.toSingle() = Single.just(this)

fun <T> T.toFlowable() = Flowable.just(this)

fun (() -> Unit).toCompletable() = Completable.fromAction(this)

// Abbreviation of schedulers
fun io() = Schedulers.io()

fun computation() = Schedulers.computation()

fun newThread() = Schedulers.newThread()

fun mainThread(): Scheduler = AndroidSchedulers.mainThread()

// Abbreviation of subscribeOn
infix fun <T> Observable<T>.subsOn(scheduler: Scheduler): Observable<T> =
    this.subscribeOn(scheduler)

infix fun <T> Single<T>.subsOn(scheduler: Scheduler) =
    this.subscribeOn(scheduler)

infix fun <T> Flowable<T>.subsOn(scheduler: Scheduler) =
    this.subscribeOn(scheduler)

infix fun Completable.subsOn(scheduler: Scheduler) =
    this.subscribeOn(scheduler)

// Abbreviation of observeOn
infix fun <T> Observable<T>.emitOn(scheduler: Scheduler): Observable<T> =
    this.observeOn(scheduler)

infix fun <T> Single<T>.emitOn(scheduler: Scheduler) =
    this.observeOn(scheduler)

infix fun <T> Flowable<T>.emitOn(scheduler: Scheduler): Flowable<T> =
    this.observeOn(scheduler)

infix fun Completable.emitOn(scheduler: Scheduler) =
    this.observeOn(scheduler)

// Only subscribe and observe on io
inline infix fun <T> Observable<T>.onIo(block: Observable<T>.() -> Observable<T>): Observable<T> =
    (block() subsOn io()) emitOn io()

inline infix fun <T> Single<T>.onIo(block: Single<T>.() -> Single<T>): Single<T> =
    (block() subsOn io()) emitOn io()

inline infix fun <T> Flowable<T>.onIo(block: Flowable<T>.() -> Flowable<T>): Flowable<T> =
    (block() subsOn io()) emitOn io()

inline infix fun Completable.onIo(block: Completable.() -> Completable): Completable =
    (block() subsOn io()) emitOn io()

// Only subscribe on io and observe on main
inline infix fun <T> Observable<T>.onMain(block: Observable<T>.() -> Observable<T>): Observable<T> =
    (block() subsOn io()) emitOn mainThread()

inline infix fun <T> Single<T>.onMain(block: Single<T>.() -> Single<T>): Single<T> =
    (block() subsOn io()) emitOn mainThread()

inline infix fun <T> Flowable<T>.onMain(block: Flowable<T>.() -> Flowable<T>): Flowable<T> =
    (block() subsOn io()) emitOn mainThread()

inline infix fun Completable.onMain(block: Completable.() -> Completable): Completable =
    (block() subsOn io()) emitOn mainThread()
