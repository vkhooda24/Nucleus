### State management

- Problem statement: When multiple thread shares the mutable property, it doesn't sync in order as
  execution happens asynchronously.

- Solutions:
    - Blocking synchronization
        - It is a blocking operation.
        - Better approach is either to use suspend function instead blocking.

    - Atomics
        - Set of atomic value and their operations are fast and guaranteed to be “thread-safe”.
        - Their operations are implemented at a low level, without locks, so this solution is
          efficient and appropriate for us.
            - There are different atomic values we can use e.g., AtomicInteger, AtomicReference.
        - In general, the utility of atomic values is very limited.
            - Because a single operation will be atomic, does not help when there are a bundle of
              operations.
        - Often use atomics to secure a single primitive or a single reference, but for more
          complicated cases.
    - Limited parallelism
        - Dispatchers.Default.limitedParallelism(1)
        - Two approaches 1. Coarse grained 2. Fine grained
    - Mutex
        - Analogy: Like a room with a single key.
        - Its most important function is `lock` .
            - When a first coroutine calls it, it kind of takes the key and passes that function
              without suspension.
                - If now another coroutine calls lock , it will be suspended until the first one
                  calls unlock.
            - If another coroutine reaches the lock function, it is suspended waiting in a queue,
              just after the second coroutine. When the first coroutine finally calls the unlock
              function, it is giving back the key, so the second coroutine (the first one in the
              queue) is now resumed, and can finally pass the lock function. This way only one
              coroutine will be between lock and unlock .
            - Better to use Mutex.withLock{} as it release lock implicitly if there is any exception
              happens.

    

