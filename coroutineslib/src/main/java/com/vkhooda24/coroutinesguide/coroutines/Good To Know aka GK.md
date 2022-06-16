### Good to know anytime (GK)

- “main” function can be suspended function and Kotlin will implicitly call this in a Coroutine.
- It is possible to create/use threads inside suspend functions.
- *Suspending functions vs regular functions*
    - Suspending functions need to pass continuations to one another. That’s why normal functions
      cannot call suspending functions whereas suspend function can call normal functions.

- By convention, `CoroutineContext` should contain an instance of a `Job` to enforce structured
  concurrency.
- Accessing `coroutineContext` property of a `CoroutineScope`:
    - In general code is not recommended for any purposes except accessing the `Job` instance for
      advanced usages.

- Avoid to use `runBlocking{}` except in UnitTest.
    - As name implies, it blocks the current thread.
    - To take advantage of coroutines, use suspended function instead in regular code except unit
      test.

- UndispatchedCoroutine - Used by withContext when context changes, but dispatcher stays the same
- DispatchedCoroutine - Used by withContext when context dispatcher changes

- Check available processors:

```kotlin
Runtime.getRuntime().availableProcessors()
```

#### In general:

- Good to launch/start a coroutine in view model because:
    - Coroutine can be cancelled in onCleared() method of view model when user navigate to other
      screen and background work is no more required.
- AAC's view model has lifecycle while repository has not lifecycle(It's just an object).
    - The repository is an optional part of the Android Architecture Components architecture.
- A repository should prefer to expose regular suspend functions that are **main-safe.**
- Dispatchers should be injected into your ViewModels so you can properly test.
    - Passing the Dispatcher via the constructor would make sure that your test and production code
      use the same dispatcher.
- Keep coroutine in same dispatcher context it started from until it must require a change. Sifting
  dispatcher context slow downs the execution.
- GlobalScope is a delicate API. It is easy to accidentally create resource or memory leaks when
  GlobalScope is used. A coroutine launched in GlobalScope is not subject to the principle of
  structured concurrency, so if it hangs or gets delayed due to a problem (e.g. due to a slow
  network), it will stay working and consuming resources.

Structured concurrency:
- Children inherit context from their parent
- A parent suspends until all the children are finished, 
- When the parent is cancelled, its child coroutines are cancelled too, when a child is destroyed, it destroys the parent as well.


Avoid Common mistake:
https://www.youtube.com/watch?v=coq9XDMB-yU