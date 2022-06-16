### Software testing

- Software testing is the process of evaluating and verifying that a software product or application
  does what it is supposed to do.
- This is an important phase of Software development lifecycle(SDLC).

#### Level of testing

- Unit testing
- Integration testing
- System testing
- Acceptance testing

#### Testing technique

- White box testing:
    - Also known as Clear Box Testing, Open Box Testing, Glass Box Testing, Transparent Box Testing,
      Code-Based Testing or Structural Testing.
    - The internal structure/ design/ implementation of the item being tested.
    - It is a testing method having knowledge about the actual code and internal structure of the
      application.
    - This type of testing is performed at a lower level of testing such as Unit Testing.
    - White box testing is done by Developers or testers with programming knowledge.

- Black box testing:
    - Like UAT testing,
    - The tester does not know the internal structures or either the code of the software.
    - It is a testing method without having knowledge about the actual code or internal structure of
      the application.
    - This is a higher level testing such as functional testing, non-functional testing.
    - Black box testing is done by the testers.

- Grey Box Testing:
    - Also known as semi-transparent technique testing
    - The testers are only partially aware of the internal structure, functions, and designs along
      with the requirements.

### Types of Black Box Testing

Practically, there are several types of Black Box Testing that are possible, but if we consider a
major variant of it then only the below mentioned are the two fundamental ones.

#### Functional Testing

- This testing type deals with the functional requirements or specifications of an application.
- Here, different actions or functions of the system are being tested by providing the input and
  comparing the actual output with the expected output.
- For example, when we test a Dropdown list, we click on it and verify if it expands and all the
  expected values are showing in the list.
- Few major types of Functional Testing are:
    - Smoke Testing
    - Sanity Testing
    - Integration Testing
        - The phase in software testing in which individual software modules(UI development, API
          integration) are combined and tested as a group.
        - Integration testing is done to test the modules/components when integrated to verify that
          they work as expected i.e. to test the modules which are working fine individually does
          not have issues when integrated.
    - System Testing
    - Regression Testing -User Acceptance

#### Non-Functional Testing

Few major types of Non-Functional Testing include:

- Usability Testing
- Load Testing
- Performance Testing
- Compatibility Testing
- Stress Testing
- Scalability Testing

#### Testing Pyramid

![Testing pyramid](/Users/vkhooda24/Screenshots/testing pyramid.png)

![Testing pyramid image](/Users/vkhooda24/Screenshots/Testing Pyramid image.png)