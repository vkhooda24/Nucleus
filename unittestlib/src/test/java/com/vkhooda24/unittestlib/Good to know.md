### Good to know

### The System Under Test (SUT) refers to a system that is being tested for correct operation.

### Depended On Component (DOC), is a collaborator, component that is required by SUT to fulfill its duties

#### Test-driven development(Black box)

- Write tests first
- Tests are black-box (designed from interface, not implementation)
- Problem: can lead to poorly structured code
- Better for features, not for infrastructure

#### Test doubles

- **Stub**
    - Stub is an object that holds predefined data and uses it to answer calls during tests. It is
      used when we cannot or don’t want to involve objects that would answer with real data or have
      undesirable side effects.
    - Stubs provide predefined expectations/canned answers to calls made during the test, usually
      not responding at all to anything outside what's programmed in for the test.
    - For example:

```kotlin
when (morning.mealOptions()).thenReturn(options("tea", "cereal", "fruits"));//stubbing meal options
```

- **Spies** are stubs that also record some information based on how they were called.
    - Uses state verification.

#### My preference: white-box tests

- Design tests by looking at the implementation; make sure every aspect is tested.
- Isomorphism: test structure matches code structure
    - One test file per code file
    - One group of tests per method, in the same order
    - Makes it easy to find relevant tests after code modifications
- Include the name of the method and the thing being tested in the test name:
    - test_loadTweetFile_cantOpenFile
- May not need any other documentation in tests

- #### Isolation and Mock.
  Any dependency on other components, external or internal should be mocked. That is the hardline of
  TDD developer. Mocking in my view is the most difficult part. Your code must be well structured
  and adhere to SOLID principle. The scope of your classes and modules must be well defined so that
  when they are tested, you can mock the dependencies and vice versa.
- **Write Test First makes one think more clearly.** On the contary, performing TDD or writing test
  before added complexity in mind when writing an implementation. As if somehow there is more
  information in a test than in code. I have practiced TDD for 1 year and I am a Software Engineer
  at Test Automation before being a backend engineer, writing 100 unit tests and 67 end-end
  integration test. I can confirm that TDD discipline does not make one a better programmer.
  **Practice on good designs and programming does!**
- Mock objects encourage testing based on behavior verification.
  [Reference read](https://web.stanford.edu/~ouster/cgi-bin/cs190-spring16/lecture.php?topic=testing)

- **The truth is that writing good tests is as hard as writing good production code. It takes
  practice and it requires careful thought, but so many times I have seen how tests are treated as
  second class citizens, which usually means that we do not reap the whole benefits of a good test
  suite, ending up with suites that are more of a burden than an asset.**

- Follow the **"3-As"** pattern for test methods: **Arrange, Act, Assert.** Specifically, use
  separate code paragraphs (groups of lines of code separated by a blank line) for each of the As.
  Arrange is variable declaration and initialization. Act is invoking the code under test. Assert is
  using the Assert.* methods to verify that expectations were met. Following this pattern
  consistently makes it easy to revisit test code.

### Junit testing vs Mocking testing

- Testing-oriented people like to use terms like object-under-test or system-under-test to name such
  a thing. Either term is an ugly mouthful to say, but as it's a widely accepted term I'll hold my
  nose and use it. Following Meszaros I'll use System Under Test, or rather the abbreviation SUT.
- This style of testing uses state verification: which means that we determine whether the exercised
  method worked correctly by examining the state of the SUT and its collaborators after the method
  was exercised. As we'll see, mock objects enable a different approach to verification.
- Mocks use behavior verification, where we instead check to see if the order made the correct calls
  on the warehouse. We do this check by telling the mock what to expect during setup and asking the
  mock to verify itself during verification. Only the order is checked using asserts, and if the
  method doesn't change the state of the order there's no asserts at all.
- The **key difference** here is how we verify that the order did the right thing in its interaction
  with the warehouse. With state verification we do this by asserts against the warehouse's state.
  Mocks use behavior verification, where we instead check to see if the order made the correct calls
  on the warehouse. We do this check by telling the mock what to expect during setup and asking the
  mock to verify itself during verification. Only the order is checked using asserts, and if the
  method doesn't change the state of the order there's no asserts at all.
- Unit test - means testing one element(SUT/real object) of software in isolation(dependencies
  mocked/stubbed expectations)
- Integration test - means testing multiple element which use dependencies as a real object, not
  mocked.
  ### Classical and Mockist Testing
    - The classical TDD style is to use real objects if possible and a double if it's awkward to use
      the real thing. So a classical TDDer would use a real warehouse and a double for the mail
      service. The kind of double doesn't really matter that much.
    - A mockist TDD practitioner, however, will always use a mock for any object with interesting
      behavior. In this case for both the warehouse and the mail service.

- [Learn more](https://martinfowler.com/articles/mocksArentStubs.html)

#### InstantTaskExecutorRule
- InstantTaskExecutorRule is a JUnit Rule. When you use it with the @get:Rule annotation, it causes some code in the InstantTaskExecutorRule class to be run before and after the tests (to see the exact code, you can use the keyboard shortcut Command+B to view the file).
- This rule runs all Architecture Components-related background jobs in the same thread so that the test results happen synchronously, and in a repeatable order. When you write tests that include testing LiveData, use this rule!

#### Resources:

https://mtlynch.io/good-developers-bad-tests/
https://dev.to/rfornal/bad-test-bad-1og7
https://blog.pragmatists.com/test-doubles-fakes-mocks-and-stubs-1a7491dfa3da
https://riis.com/blog/unit-testing-good-vs-bad-tests/
https://www.allankelly.net/archives/628/testing-triangles-pyramids-and-circles/
https://danlebrero.com/2016/11/06/good-test-vs-bad-tests/
https://www.testbytes.net/blog/unit-testing-best-practices/
https://leanylabs.com/blog/good-unit-tests/
Code
coverage: https://www.ponicode.com/blog/the-first-article-you-should-read-on-how-to-measure-your-unit-tests
https://levelup.gitconnected.com/rethinking-unit-testing-in-software-development-11b948483ed
https://livebook.manning.com/book/unit-testing/chapter-1/
https://apiumhub.com/tech-blog-barcelona/top-benefits-of-unit-testing/
https://wiki.c2.com/?ArrangeActAssert

[Best practices Unit test](https://phauer.com/2018/best-practices-unit-testing-kotlin/)

