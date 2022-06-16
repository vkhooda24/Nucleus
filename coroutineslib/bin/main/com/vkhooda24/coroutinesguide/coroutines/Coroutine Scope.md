### Coroutine Scope

- `CoroutineScope` is an interface
    - `CoroutineContext` is encapsulated by the scope.
    - Used for implementation of coroutine builders that are extensions on the scope.
- Coroutine library exposes a function named `CoroutineScope` to initialize `CoroutineScope`
  internally.
- Accessing `coroutineContext` property of a `CoroutineScope`
  e.g., `coroutineContext[CoroutineDispatcher]`
    - In general code is not recommended for any purposes except accessing the `Job` instance for
      advanced usages.
- `CoroutineScope` always keep an instance of a `Job` to manage the structured concurrency.
- `coroutineContext` and `currentCoroutineContext()`
    - Both returns the `coroutineContext` instance.
    - `currentCoroutineContext()` added as an alias to avoid name clash
      with `CoroutineScope.coroutineContext` in a receiver position.
- #### CoroutineScope extension functions
    - plus(context: CoroutineContext):
        - To override the existing context element in current scope.
    - ensureActive():
        - Ensures that current scope is active.
        - If the job is no longer active, throws `CancellationException`.
    - cancel(cause: CancellationException? = null):
        - Cancels the current scope, including its job and all its children with an optional
          cancellation cause.
            - A cancellation cause can be used to specify an error message or to provide other
              details on a cancellation reason for debugging purposes.
            - Throws `IllegalStateException` if the scope does not have a job in it.

#### Example: CoroutineScope usage

```kotlin
suspend fun main() {
    val context = EmptyCoroutineContext + Dispatchers.Default
    val scope = CoroutineScope(context)
    scope.launch {
        println("Coroutine launch builder is Coroutine scope's extension function")
    }.join()
}
```

#### CoroutineScope Interface

```kotlin
public interface CoroutineScope {
    public val coroutineContext: CoroutineContext
}
```

#### CoroutineScope internal initialization implementation

```kotlin
internal class ContextScope(context: CoroutineContext) : CoroutineScope {
    override val coroutineContext: CoroutineContext = context
    override fun toString(): String = "CoroutineScope(coroutineContext=$coroutineContext)"
}
```

#### CoroutineScope Function

```kotlin
@Suppress("FunctionName")
public fun CoroutineScope(context: CoroutineContext): CoroutineScope =
    ContextScope(if (context[Job] != null) context else context + Job())
```

### CoroutineScope(), MainScope(), GlobalScope object and coroutineScope{}

- CoroutineScope() function:
    - It bounds to a Job when creates coroutine scope with a specified context.
- MainScope() function:
    - Runs on main thread as it sets `Dispatchers.Main` dispatcher internally.
    - It is bound with SupervisorJob.
    - On Android, MainScope() is safer instead GlobalScope object to create a scope.
- GlobalScope object:
    - Uses `EmptyCoroutineContext`.
    - It is not bound to any job.
- coroutineScope{} suspend function:
    - Creates a `CoroutineScope` and it's a suspend function.
    - Calls the specified suspend block with this scope.
    - This scope inherits its `coroutineContext` from the outer scope, but overrides the context's
      Job.
    - This function is designed for parallel decomposition of work.
        - When any child coroutine in this scope fails, this scope fails and all the rest of the
          children are cancelled.
            - `SupervisorJob` can be used to handle the bi-directional error propagation.
        - This function returns as soon as the given block and all its children coroutines are
          completed.

#### CoroutineScope Function

```kotlin
@Suppress("FunctionName")
public fun CoroutineScope(context: CoroutineContext): CoroutineScope =
    ContextScope(if (context[Job] != null) context else context + Job())
```

#### Main scope function implementation

```kotlin
@Suppress("FunctionName")
public fun MainScope(): CoroutineScope = ContextScope(SupervisorJob() + Dispatchers.Main)
```

#### GlobalScope object implementation

```kotlin
public object GlobalScope : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = EmptyCoroutineContext
}
```

#### coroutineScope{} suspend function

```kotlin
public suspend fun <R> coroutineScope(block: suspend CoroutineScope.() -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return suspendCoroutineUninterceptedOrReturn { uCont ->
        val coroutine = ScopeCoroutine(uCont.context, uCont)
        coroutine.startUndispatchedOrReturn(coroutine, block)
    }
}
```

### Coroutine Scope Cancellation

```kotlin
public fun CoroutineScope.cancel(cause: CancellationException? = null) {
    val job = coroutineContext[Job]
        ?: error("Scope cannot be cancelled because it does not have a job: $this")
    job.cancel(cause)
}

//OR

public fun CoroutineScope.cancel(message: String, cause: Throwable? = null): Unit =
    cancel(CancellationException(message, cause))

```