### Coroutine Job

- #### Job:
    - An interface which extends `CoroutineContext.Element`.
        - `Job` can be used as a key because it holds an object of `CoroutineContext.Key<Job>` type.
        - A Job instance in the `coroutineContext`  represents the coroutine itself.
    - It's a background Job which is a cancellable thing with a life-cycle.
    - #### Job object creation:
        - Create with Job() factory function.
        - OR created with launch coroutine builder.
            - It runs a specified block of code and completes on completion of this block.
    - Conceptually, an execution of a job does not produce a result value.
        - Jobs are launched solely for their side-effects.
        - Deferred interface for a job that produces a result.
    - #### Job life-cycle:
        - New and Start state:
            - Usually, a job is created in the active state (it is created and started).
                - However, coroutine builders that provide an optional start parameter create a
                  coroutine in the **new** state when this parameter is set to `CoroutineStart.LAZY`
                  .
                    - Such a job can be made active by invoking start or join.
            - A job is active while the coroutine is working or until CompletableJob is completed,
              or until it fails or cancelled.
        - Cancellation state:
            - Failure of an active job with an exception makes it cancelling.
                - A job can be cancelled at any time with cancel function that forces it to
                  transition to the cancelling state immediately.
                - The job becomes cancelled when it finishes executing its work and all its children
                  complete.
            - A coroutine job is said to complete exceptionally when its body throws an exception;
                - A CompletableJob is completed exceptionally by
                  calling `CompletableJob.completeExceptionally`.
                    - An exceptionally completed job is cancelled and the corresponding exception
                      becomes the cancellation cause of the job.
            - Normal cancellation of a job is distinguished from its failure by the type of this
              exception that caused its cancellation.
                - A coroutine that threw `CancellationException` is considered to be cancelled
                  normally.
                    - If a cancellation cause is a different exception type, then the job is
                      considered to have **failed, not cancelled**.
            - cancel() function on a job only accepts `CancellationException` as a cancellation
              cause:
                - thus calling cancel() always results in a normal cancellation of a job, which does
                  not lead to cancellation of its parent.
                    - This way, a parent can cancel its own children (cancelling all their children
                      recursively, too) without cancelling itself.
        - Completion state:
            - Completion of an active coroutine's body or a call to `CompletableJob.complete`
              transitions the job to the completing state.
                - It waits in the completing state for all its children to complete before
                  transitioning to the completed state.
                    - **Note** that completing state is purely internal to the job.
                        - For an outside observer a completing job is still active, while internally
                          it is waiting for its children.
    - #### Concurrency and synchronization
        - All functions on this interface are **thread-safe**.
            - It can be safely invoked from concurrent coroutines without external synchronization.
    - #### Important properties:
        - `isActive`
            - Already started and has not completed nor was cancelled yet.
            - The job that is waiting for its children to complete is still considered to be active
              if it was not cancelled nor failed.
        - `isCompleted`
            - A job that was cancelled or failed and has finished its execution is also considered
              complete.
            - Job becomes complete only after all its children complete.
        - `isCancelled`
            - Job cancelled either by explicit invocation of `cancel()`
                - or because it had failed or its child or parent was cancelled.
            - Cancelled job does not imply that the job has already completed
                - because it may still be finishing whatever it was doing and waiting for its
                  children to complete.
    - #### Important method:
        - `start()`:
            - When coroutine related to this job (if any) if it was not started yet.
            - Returns result a boolean value:
                - True if this invocation actually started coroutine
                - False if it was already started or completed.
        - `cancel()`:
            - Cancels this job with an optional cancellation cause.
                - A cause can be used to specify an error message
                    - or to provide other details on the cancellation reason for debugging purposes.
            - cancel() function on a job only accepts `CancellationException` as a cancellation
              cause:
                - thus calling cancel() always results in a normal cancellation of a job, which does
                  not lead to cancellation of its parent.
                    - This way, a parent can cancel its own children (cancelling all their children
                      recursively, too) without cancelling itself.
            - Calling from cancellation vs on job produces different result.
              Inside `coroutineScope{}` cancel() call would not cancel the job immediate and can
              perform few actions while it's canceling whereas cancel() on job would immediate
              cancel and doesn't execute the given block.
        - `join()`:
            - This is a suspend function.
            - Suspends the coroutine until this job is complete.
            - This function also starts the corresponding coroutine if the Job was still in new
              state.
            - This suspending function is cancellable and always checks for a cancellation.
            - This invocation resumes normally (without exception) when the job is complete for any
              reason
                - And the Job of the invoking coroutine is still active.
            - If the Job of the invoking coroutine is cancelled
                - Or completed when this suspending function is invoked
                - Or while it is suspended
                    - `join()` function throws CancellationException.
            - **Note:** that the job becomes complete only when all its children are complete.
        - `ensureActive()`
            - Ensures that current job is active.
            - If the job is no longer active, throws CancellationException.
                - If the job was cancelled, thrown exception contains the original cancellation
                  cause.
        - `cancelAndJoin()`
            - Cancels the job and suspends the invoking coroutine until the cancelled job is
              complete.
            - A parent coroutine invoking `cancelAndJoin()` on a child coroutine
              throws `CancellationException` if the child had failed.
                - This cause a bi-directional propagation of error.
                    - This cause can be avoided if a child launched from within `supervisorScope`.
            - Shortcut for invocation of cancel() followed by join().
        - plus()/ + operator:
            - Operator '+'/plus() on two Job objects is meaningless.
                - The job to the right of `+` just replaces the job the left of `+` .

start() method signature:

```kotlin
public fun start(): Boolean
```

cancel() method signature:

```kotlin
public fun cancel(cause: CancellationException? = null)
```

join() method signature:

```kotlin
 public suspend fun join()
```

cancelAndJoin() method signature:

```kotlin
public suspend fun Job.cancelAndJoin() {
    cancel()
    return join()
}
```

#### Job children

- Property `children`
    - Provides a sequence of this job's children.

```kotlin
public val children: Sequence<Job>
```

- Parent-child relationship:
    - Jobs can be arranged into parent-child hierarchies
        - Where cancellation of a parent leads to immediate cancellation of all its children
          recursively.
        - Failure of a child with an exception other than `CancellationException` immediately
          cancels its parent and, consequently, all its other children.
        - This behavior can be customized using `SupervisorJob`.
- A parent-child relation has the following effect:
    1. Cancellation of parent with cancel
        - Or its exceptional completion (failure) immediately cancels all its children.
    2. Parent cannot complete until all its children are complete.
        - Parent waits for all its children to complete in completing or cancelling state.
    3. Uncaught exception in a child, by default, cancels parent.
        - This applies even to children created with `async` and other future-like coroutine
          builders
            - Even though their exceptions are caught and are encapsulated in their result.

#### Parent child communication

ChildJob interface:

```kotlin
public interface ChildJob : Job {
    @InternalCoroutinesApi
    public fun parentCancelled(parentJob: ParentJob)
}
```

ParentJob interface:

```kotlin
public interface ParentJob : Job {
    @InternalCoroutinesApi
    public fun getChildJobCancellationCause(): CancellationException
}
```

Cancel job:

```kotlin
public fun CoroutineContext.cancel(cause: CancellationException? = null) {
    this[Job]?.cancel(cause)
}
```

Cancel children:

```kotlin
public fun CoroutineContext.cancelChildren(cause: CancellationException? = null) {
    this[Job]?.children?.forEach { it.cancel(cause) }
}
```

isActive property:

```kotlin
public val CoroutineContext.isActive: Boolean
    get() = this[Job]?.isActive == true
```

Ensure active method:

```kotlin
public fun Job.ensureActive(): Unit {
    if (!isActive) throw getCancellationException()
}
```

DisposableHandle interface:

- Disposes the corresponding object.
- Makes coroutines resources eligible for garbage collection.
    - Repeated invocation of this function has no effect.

```kotlin
public fun interface DisposableHandle {
    public fun dispose()
}
```

#### invokeOnCompletion()

- Registers handler that is synchronously invoked once on completion of this job.
    - When the job is already complete, then the handler is immediately invoked with the job's
      exception or cancellation cause or null.
        - Otherwise, the handler will be invoked once when this job is complete.
    - The meaning of cause that is passed to the handler:
        - Cause is null when the job has completed normally.
        - Cause is an instance of CancellationException when the job was cancelled normally. **It
          should not be treated as an error.**
        - Otherwise, the job had failed.
        - The resulting DisposableHandle can be used to dispose the registration of this handler and
          release its memory if its invocation is no longer needed.
            - **Note:** There is no need to dispose the handler after completion of this job.
                - The references to all the handlers are released when this job completes.
        - **Note:** Implementation of CompletionHandler must be fast, non-blocking, and thread-safe.
            - This handler can be invoked concurrently with the surrounding code.
            - There is no guarantee on the execution context in which the handler is invoked.

```kotlin
 public fun invokeOnCompletion(handler: CompletionHandler): DisposableHandle
```