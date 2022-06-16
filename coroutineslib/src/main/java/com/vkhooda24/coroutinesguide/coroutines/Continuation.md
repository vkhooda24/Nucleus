### Continuation

- Key classes and interface:
    - I:  Continuation
    - AC: ContinuationImpl
    - AC: BaseContinuationImpl

- Interface representing a continuation after a suspension point
    - It declares a property `CoroutineContext` and a function `resumeWith(result: Result<T>)`
    - It returns a value of type T.
- Property `CoroutineContext`:
    - Context of the coroutine that corresponds to this continuation.
- Function `resumeWith(result: Result<T>)`:
    - Resumes the execution of the corresponding coroutine passing a successful or failed result as
      the return value of the last suspension point.
- When a function suspend, that returns a `Continuation` object.
- Suspending functions need to pass continuations to one another. That’s why normal functions cannot
  call suspending functions whereas suspend function can call normal functions.
- "Continuation" could be serialized, deserialized and then resume.
- We can pass `Continuation` object as an argument to other functions.
- A continuation should not call `resumeWith()` twice. It would throw runtime exception "
  Already resumed".

```kotlin
public interface Continuation<in T> {
    public val context: CoroutineContext
    public fun resumeWith(result: Result<T>)
}
```