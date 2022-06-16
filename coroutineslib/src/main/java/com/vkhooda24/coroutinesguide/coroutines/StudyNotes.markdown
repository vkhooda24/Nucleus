### Notes

Functional vs imperative

- Parallel stream
    - Structure of [functional] sequential code is same as the parallel code
    - In Imperative style, it is hard to achieve parallelism in few steps like we can do in
      functional style.
    - Functional programming is excellent when dealing with pure function but not great when dealing
      with non-pure functions e.g., IO operation, database operations and their exception handling?
    - Handling exception in functional programming is messy/complex. Explore why?

- Coroutine
    - Structure of [imperative] sequential code is same as the structure of asynchronous code.

#### Explore:

- How to write parallelism code in imperative style?
- Why Handling exception in functional programming is messy/complex?
- Right place to start coroutines for fire and forget calls, e.g., analytics call.
- When starting a new coroutine in response to a UI event, consider what happens if the user starts another before this one completes?
    - State management control requires to keep data state consistent otherwise there would be concurrency bug introduce.