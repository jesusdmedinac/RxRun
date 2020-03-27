package com.mupper.reactiveelements

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.Flowable
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun Any.toObservable() = Observable.just(this)

fun Any.toSingle() = Single.just(this)

fun Any.toFlowable() = Flowable.just(this)

fun (() -> Unit).toCompletable() = Completable.fromAction(this)

// Abbreviation of schedulers
fun io() = Schedulers.io()

fun computation() = Schedulers.computation()

fun newThread() = Schedulers.newThread()

fun mainThread() = AndroidSchedulers.mainThread()

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
