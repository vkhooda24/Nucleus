### Coroutine Dispatchers

#### CoroutineDispatcher:

- Allow us to decide on which thread (or pool of threads) a coroutine should be running (starting
  and resuming)
- Provide as a coroutine context to a scope.
- `CoroutineDispatcher` implement the coroutine context `Element` interface which holds the key.
    - That's why `CoroutineDispatcher` can be used as a key itself to know the running dispatcher
      details.

#### Type of dispatchers:

- Default
- Main
- IO
- Confined

#### Default Dispatcher:

**API Signature**

```kotlin
@JvmStatic
public actual val Default: CoroutineDispatcher = DefaultScheduler
```

**Usage example**

```kotlin
val scope = CoroutineScope(Dispatchers.Default)
```

- Default dispatcher will be used implicitly if no dispatcher is provided to coroutine scope.
- It is designed to run CPU-intensive operations.
- It has a pool of threads
    - with a size equal to the number of cores on the machine your code is running on
    - (but not less than two).
- This dispatcher shares threads with a `Dispatchers.IO` dispatcher
    - Using withContext(Dispatchers.IO) { ... } does not lead to an actual switching to another
      thread.

#### Main dispatcher:

**API Signature**

```kotlin
@JvmStatic
public actual val Main: MainCoroutineDispatcher
    get() = MainDispatcherLoader.dispatcher
```

- Uses for UI application which runs on main thread, e.g., Android application, Swing, JavaFx
  application.
- API exists in `kotlinx-coroutines-android` library.
    - `immediate` main dispatcher:
    - If new assigned dispatcher is the same as parent's running dispatcher
        - Then no context switching happenings(No re-dispatching).
        - `immediate` dispatcher is used as a performance optimization to avoid unnecessary
          dispatching.
        - Save resources and time that happens because of creating new dispatcher.
        - If `immediate` dispatching is not supported by current dispatcher
            - Throws ` UnsupportedOperationException `

```kotlin
public abstract val immediate: MainCoroutineDispatcher
```

**Usage example**

```kotlin
withContext(Dispatchers.Main.immediate) {
    //ui work
}
```

https://medium.com/@bhavnathacker14/deep-dive-into-dispatchers-for-kotlin-coroutines-f38527bde94c

Types of Dispatchers

- There are basically four types of Dispatchers:

    - Dispatchers.Main: A coroutine dispatcher that is confined to the Main thread operating with UI
      objects. Usually such dispatcher is single-threaded.
    - Dispatchers.Default: The default CoroutineDispatcher that is used by all standard builders
      like launch, async, etc. if no dispatcher nor any other ContinuationInterceptor is specified
      in their context.
    - Dispatchers.IO: The CoroutineDispatcher that is designed for offloading blocking IO tasks to a
      shared pool of threads.
    - Dispatchers.Unconfined: A coroutine dispatcher that is not confined to any specific thread. It
      executes initial continuation of the coroutine in the current call-frame and lets the
      coroutine resume in whatever thread that is used by the corresponding suspending function,
      without mandating any specific threading policy.
        - The Dispatchers.Unconfined coroutine dispatcher starts a coroutine in the caller thread,
          but only until the first suspension point. After suspension it resumes the coroutine in
          the thread that is fully determined by the suspending function that was invoked.

Difference between IO and Default Dispatcher

- The main difference between these two is as below:
  Default Dispatcher (preferred for operations you want to off-load from the main thread like
  CPU-intensive operations):
  ```
   By default, the maximal level of parallelism used by this dispatcher is equal to the number of CPU
   cores, but is at least two. Level of parallelism X guarantees that no more than X tasks can be
   executed in this dispatcher in parallel.
  ```
- IO Dispatcher (preferred for heavy IO operations like read/write files, uploading or
  decrypting/encrypting files):

```
    The number of threads used by tasks in this dispatcher is limited by the value of “kotlinx.coroutines.io.parallelism” (IO_PARALLELISM_PROPERTY_NAME) system property. 
    It defaults to the limit of 64 threads or the number of cores (whichever is larger).

```