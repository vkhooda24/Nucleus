### Unit testing frameworks

#### Junit

- JUnit vs Mock framework:
    - Junit is a framework that helps with writing and running the unit tests with real object of
      production classes.
    - Other mocking framework like Mockito, Mockk help writing unit test effectively with mock
      objects for any dependencies which required to create real object of production calls. Also,
      provide stubbing for mock methods calls.
    - Mocking means only dependencies/collaborator objects get mock whereas System under test(SUT)
      object remains a real object.
        - On mock object's method, always need to set an expectation when SUT object calls methods
          to test.
- it is very cumbersome to test the scenarios where there is a dependency between the classes.
- @BeforeClass annotation
    - applies only `public static void` methods.
- @Before annotation
    - Applies to any `public void` methods.
- @AfterClass annotation
    - applies only `public static void` methods.
- @After annotation
    - Applies to any `public void` methods.
- @test annotation:
    - Represents a test.
    - Method must be public.

#### Assertion

Assertion class (a predicate) used to verify a programming assumption (expectation) with an actual
outcome of a program implementation.

- It's a static class.
- Package `org.junit.Assert`
    - Provides static overloaded methods to assert expected and actual values for all primitive
      types, objects, and arrays.

### Mockito framework:

- `mock()/@Mock` create mock object.
- `spy()/@Spy` partial mocking, real methods are invoked but still can be verified and stubbed.
    - Mockito *does not* delegate calls to the passed real instance, instead it actually creates a
      copy of it. So if you keep the real instance and interact with it, don't expect the spied to
      be aware of those interaction and their effect on real instance state.
    - [Learn more](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#spy-T-)
- `when()/given()` to specify how a mock should behave.
- `verify()` to check methods were called with given arguments
    - can use flexible argument matching, for example any expression via the any()
    - or capture what arguments were called using @Captor instead Junit vs Mock frameworks:
- [Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Github wiki](https://github.com/mockito/mockito/wiki)

### Mockk framework:

#### Relaxed mocks 

- By default mocks are strict. Before passing mock to a code being tested you should set behavior
  with every block. In case you do not provide expected behavior, and call is performed, library
  throws an exception.
    - This is different from what Mockito is doing by default. Mockito allows you to skip specifying
      expected behavior and replies with some basic value alike null or 0. You can achieve the same
      and even more in MockK by declaring relaxed mock.
    - [Learn more](https://blog.kotlin-academy.com/mocking-is-not-rocket-science-mockk-features-e5d55d735a98)
- **Spy** - Spies give the possibility to set expected behavior and do behavior verification while still executing original methods of an object.

https://stackoverflow.com/questions/38747779/mockito-what-is-it-and-how-is-it-different-from-junit
https://martinfowler.com/articles/mocksArentStubs.html