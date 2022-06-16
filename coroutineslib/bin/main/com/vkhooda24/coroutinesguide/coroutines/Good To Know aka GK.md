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

- Prevent using runBlocking except UnitTest.
    - As name implies, it blocks the current thread.
    - To take advantage of coroutines, use suspended function instead in regular code except unit
      test.