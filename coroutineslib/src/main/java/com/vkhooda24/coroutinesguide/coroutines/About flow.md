#### Flow

Flow: Used to return multiple values

Context preservation:

Wrong emission withContext

Use flowOn() for context switching for a flow instead using a withContext()

- The flowOn operator creates another coroutine for an upstream flow when it has to change the
  CoroutineDispatcher in its context.