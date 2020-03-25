package com.mupper.reactiveelements

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

fun Any.toObservable() = Observable.just(this)

fun Any.toSingle() = Single.just(this)

fun Any.toFlowable() = Flowable.just(this)

fun (() -> Unit).toCompletable() = Completable.fromAction(this)