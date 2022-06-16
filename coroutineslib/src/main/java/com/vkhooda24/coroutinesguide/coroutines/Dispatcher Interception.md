### Interceptor

- Intercept the coroutine continuation.
- `ContinuationInterceptor` is an interface.
    - Uses `ContinuationInterceptor.Key` to retrieve and intercept all the continuations.
    - It happens using `interceptContinuation(continuation: Continuation<T>)` function invocations.


