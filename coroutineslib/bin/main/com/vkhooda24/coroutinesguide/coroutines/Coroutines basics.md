### Coroutines basics

- Coroutines are asynchronous
- Kotlin's coroutines are hygienic syntax that makes it easy to use.
- Synchronous
    - Running on the same thread and waiting for process to complete.
- Asynchronous
    - Running on the same thread while process goes to another thread to finish. Once finish, it
      sends back the result to parent thread.

#### Continuation

Call a function which returns some result along with a continuation which gives caller function a
way to keep continue engagement.

Analogy:
Like watching a video and pause it at some point, come back at later time and resume where you pause
it earlier. Chatting to someone on a topic and start at later time to start where you left the
conversation earlier.

### Order of statements execution

- When number of statements in order in one launch coroutines builder then statements would execute
  in given order i.e., sequential
    - It behaves same as happens in non-coroutines scenario.

- When we need asynchronous execution, then wrap each statement in a coroutine builder i.e., launch,
  async
  