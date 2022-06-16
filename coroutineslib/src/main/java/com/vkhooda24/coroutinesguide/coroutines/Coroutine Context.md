### Coroutine Context

- `CoroutineContext` is an interface and important pillar of coroutines.
- Coroutine starts with a context and keep tied until coroutine's job completes/cancelled.
- It is an indexed set of `Element` instances.
    - An **indexed set** is a mix between a set and a map.
    - Every element in this set has a **unique Key**.
    - `Element` implements the `CoroutineContext`.
        - An element of the coroutine context is a singleton context by itself.
- By default, any coroutine builder or scope function uses an empty coroutine
  context `EmptyCoroutineContext`.
- Accessing `coroutineContext` property of a `CoroutineScope` in general code is not recommended for
  any purposes except accessing the `Job` instance for advanced usages.

#### Code blocks

- `CoroutineContext` interface holds these given below three blocks of code.

```kotlin
public interface CoroutineContext {
    public operator fun <E : Element> get(key: Key<E>): E?
    public operator fun plus(context: CoroutineContext): CoroutineContext {}
    public fun minusKey(key: Key<*>): CoroutineContext
    public fun <R> fold(initial: R, operation: (R, Element) -> R): R
}
```

```kotlin
public interface Key<E : Element>
```

```kotlin
public interface Element : CoroutineContext {
    // A key of this coroutine context element.
    public val key: Key<*>

    public override operator fun <E : Element> get(key: Key<E>): E? {}
    public override fun minusKey(key: Key<*>): CoroutineContext = {}
    public override fun <R> fold(initial: R, operation: (R, Element) -> R): R {}
}
```

- A few examples of `CoroutineContext`'s `Element` are:
    - EmptyCoroutineContext, CombinedContext
    - AbstractCoroutineContextElement
    - EventLoop, BlockingEventLoop
    - Dispatcher, DefaultScheduler

### Example

```kotlin
@OptIn(ExperimentalStdlibApi::class)
suspend fun main() {

    val coroutineName = CoroutineName("Name: EmptyCoroutineContext") + EmptyCoroutineContext
    val job = GlobalScope.launch(coroutineName) {
        val dispatcher = coroutineContext[CoroutineDispatcher]
        val name = coroutineContext[CoroutineName]?.name
        println("Coroutine Context details: $name , Dispatcher: $dispatcher")
    }
    job.join()
}
```
    