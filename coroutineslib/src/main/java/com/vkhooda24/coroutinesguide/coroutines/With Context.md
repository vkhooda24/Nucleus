## With context

- It is `suspend` function that can modify coroutine context of a block.
- It belongs to coroutine scope functions family.
    - *This isn't a coroutine builder function*.
- It takes second parameter as `CoroutineScope` lambda with receiver type block
  i.e. `CoroutineScope.() ->T`
- Calls the specified suspending block with a given `CoroutineContext`, suspends until it completes,
  and returns the result.
- It only shifts the execution of block from current thread to any other assigned thread.
- It doesn't achieve execution of statements in asynchronous fashion.
- For asynchronous execution of operations, instead using `withContext`, better to use a coroutine
  builder or coroutine scope function.
- If parent or parent's children throws cancellation exception, `withContext` would also be
  cancelled with `cancellationException`.
- When we use `withContext(SupervisorJob())`, then withContext is still using a regular Job , and
  the
  `SupervisorJob()` becomes its parent. As a result, when one child raises an exception, the other
  children will be cancelled as well. `withContext()` will also throw an exception, so its
  `SupervisorJob()` is practically useless.
- Always better to use `withContext(Dispatchers.Main.immediate)` instead `withContext (
  Dispatchers.Main)` because when a function is being called on the main thread already, it won’t be
  re-dispatched, it will be called immediately. Android uses main thread to update the UI so parent
  function usually dispatch as `Dispatchers.Main`.
- `withContext(NonCancellable)` changes the context for a block of code.
    - This `NonCancellable` object is a Job that cannot be canceled. So inside the block, the job is
      in the active state, and we can call whatever suspending functions we want.
- `withContext(EmptyCoroutineContext)` and `coroutineScope()` behave exactly the same way.
- When `withContext` calls, it first checks if the new dispatcher context is same as provided by
  parent or different.
    - If it is same then it only starts a new coroutine with given dispatcher context without
      changing the parent `Job` context.

#### Signature

```kotlin
public suspend fun <T> withContext(
    context: CoroutineContext,
    block: suspend CoroutineScope.() -> T
): T {
}
```

#### Example

```kotlin
withContext(Dispatchers.Default) {
    checkEmails()
}
```

- `WithContext()` can be used instead of using `async{}.await()` coroutine builder.

```kotlin
withContext(Dispatchers.Default) {
    println("With context output")
}
```

```kotlin
async {
    "Async output"
}.await()
```