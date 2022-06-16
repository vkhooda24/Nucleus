### Coroutines opening

- To launch a coroutine, it requires to be in a coroutine scope and that needs a coroutine context.
- Coroutine Scope provides a platform aka space/boundary as name suggest.
- Coroutines context provides the tools aka information about launch of this coroutine.
- These informational things can be such as coroutine name, which thread to run a given coroutine,
  coroutine lifecycle, how to handle if any error and more.
  
Technically:
- Coroutine dispatcher that determines what thread or threads the corresponding coroutine uses for its execution.
- Dispatchers in coroutines are similar to RxJava schedulers.
  
