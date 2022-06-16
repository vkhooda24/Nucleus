### Coroutines basics

- Coroutines are asynchronous
- Kotlin's coroutines are hygienic syntax that makes it easy to use.
- Synchronous
    - Running on the same thread and waiting for process to complete.
- Asynchronous
    - Running on the same thread while process goes to another thread to finish. Once finish, it
      sends back the result to parent thread.
- Coroutine works on a suspendable computation in which it suspends a piece of program to execute on
  a separate worker thread while the rest of the program can continue with the execution.

#### Important classes, interfaces and files

- F: Builders.common.kt

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

```kotlin
suspend fun main() {
    beforePhoneCallConversation()
    takePhoneCall()
    afterPhoneCallConversation()
}
suspend fun takePhoneCall() {}

fun beforePhoneCallConversation() {
    //Hello, How are you?
    //Hey, I am good.
    //Just a sec, my brother is calling.
}
fun afterPhoneCallConversation() {
    //Sorry, I had to take that call.
    //No problem.
}
```