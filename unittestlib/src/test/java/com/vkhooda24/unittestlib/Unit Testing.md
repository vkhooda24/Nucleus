### Unit testing

- Part of software development.
- Writing a piece of code (unit test) to verify the code (unit) written for implementing
  requirements.
- Unit testing is an insurance for your production code.
- Classic definition:
    - “Unit Testing is the method of verifying the smallest piece of testable code against its
      purpose.”
    - If the purpose or requirement fails then the unit test has failed.

#### Analogy

- Business logic in software development is like an act of the life.
- Unit testing is like an introspection in the life.
    - Summary:
        - Business logic is like an act in our life, and when we perform the act, we expect some
          result of that act. Introspection(Unit testing) of that act checked as expectation vs
          reality helps in improving the life quality. - In short:

#### Testing methods

- Manual
    - Developer writes and tests unit test on his/her machine.
- Automated
    - Software tools used to run the unit test in build process and can run at any specified time or
      at interval.

#### Advantages of unit testing

- Detects bug early:
    - Identification and resolve of an issue happens during development cycle.
    - Bugs found earlier in the development cycle are much cheaper to fix than those found later.
- Need of debugging reduces which saves time.
- Easy debugging process:
    - Helps in simplifying the debugging process. If the test fails at any stage the code needs to
      be debugged or else the process can be continued without any obstacles.
- Code readability, re-usability and quality improves.
- Easier changes and simplified integrations:
    - Easier to add or update new/existing functionality at later point.
- Code coverage can be measured.

#### Disadvantages

- Initial time investment to take care testing of every single line of business logic.
- Steep learning curve.
- Lack of providing sufficient time to a developer can force writing a bad unit test(could be empty
  test).

#### Misconceptions and Truths

It takes more time to write code with Unit test cases, and we don’t have time for that – In reality,
it would save your development time in the long run. Unit testing will find all bugs – It won’t, as
the intent of the Unit test is not to find bugs but to develop robust software components that will
have fewer defects in the later stages of SDLC. 100% code coverage means 100% test coverage – This
does not guarantee that the code is error-free.

### References:

https://jesusvalerareales.medium.com/testing-with-test-doubles-7c3abb9eb3f2