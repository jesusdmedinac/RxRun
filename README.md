# RxRun

[![](https://jitpack.io/v/dmc12345628/RxRun.svg)](https://jitpack.io/#dmc12345628/RxRun)

A compilation of shortcuts of RxJava functions.

## Installation

This library is on [jitpack server](https://jitpack.io/#dmc12345628/RxRun). You can follow the instructions on the page.

## Abbreviations

You can find next abbreviations for the RxJava and RxAndroid basic threads.

```kotlin
// Abbreviation of schedulers
fun io() = Schedulers.io()

fun computation() = Schedulers.computation()

fun newThread() = Schedulers.newThread()

fun mainThread(): Scheduler = AndroidSchedulers.mainThread()
```
 
## Run on io

When you want to do a subscription on io thread and observe it on the same io thread, you can use runOnIo:

```kotlin
val observable = Observable.just("Some text")

observable.runOnIo({ error ->
    error.printStackTrace()
}) {
    println("Print $it on io thread")
}

// The error will implicitly call error.printStackTrace, so you can avoid

observable.runOnIo {
    println("Print $it on io thread")
}
```

## Run on main

When you want to do a subscription on io thread and observe it on the main thread, you can use runOnMain:

```kotlin
val observable = Observable.just("Some text")

observable.runOnMain({ error ->
    error.printStackTrace()
}) {
    println("Print $it on main thread")
}

// The error will implicitly call error.printStackTrace, so you can avoid

observable.runOnMain {
    println("Print $it on main thread")
}
```

## Run from lambda action

You can even create a lambda action and run it on io or main thread.

```kotlin
val lambdaAction = {
    println("Print it on lambda action")
}

lambdaAction.runOnIo {
    println("Another message on io thread")
}

lambdaAction.runOnMain {
    println("Another message on main thread")
}
```

## Get on io

When you need to use a `blockingFirst`, `blockingGet` or `blockingAwait` on io thread, you can use getOnIo.

```kotlin
val observable = Observable.just("Some text")
val someText: String = observable.getOnIo()
```

## Get on main

When you need to use a `blockingFirst`, `blockingGet` or `blockingAwait` on main thread, you can use getOnMain.

```kotlin
val observable = Observable.just("Some text")
val someText: String = observable.getOnMain()
```

## Convert any value to reactive element

You can convert any type value to observable, single, flowable or even convert a lambda action to completable.

```kotlin
"Some text".toObservable()

"Some text".toSingle()

"Some text".toFlowable()

{ println("Some text") }.toCompletable()
```

## More abbreviations

### subsOn for subscribeOn

```kotlin
val observable = Observable.just("Some text")

observable subsOn io()
```

### emitOn for observeOn

```kotlin
val observable = Observable.just("Some text")

observable emitOn io()
```

### subsOn and emitOn on io

You can call subsOn and emitOn on io thread

```kotlin
val observable = Observable.just("Some text")

observable onIo {
    // You can add here any observable function 
    this
}
```

### subsOn on io thread and emitOn on main thread

You can call subsOn io thread and emitOn on main thread

```kotlin
val observable = Observable.just("Some text")

observable onIo {
    // You can add here any observable function 
    this
}
```

## LiveDataReactiveStreams

There are to simple function to convert your LiveDatas to Publisher and your Publishers to LiveData.

```kotlin
val flowable = Flowable.just(Unit)

val liveData = flowable.toLiveData()

val publisher = liveData.toPublisher()
```
