# Car Shop Assignment

Assignment for Java Backend Engineer candidates in Eleveo.

## Description

This project contains an assignment description and definition of the API needed to fulfill the assignment.
Also, a prepared example of client implementation is provided.

### Assignment

As a personal shopper, you have a rich client. They frequently ask you to buy new cars as an addition to their garage.
Today they requested the following:

> I don`t care about the car price, I want to buy the 5 most powerful ones - the ones with the highest horsepowers.
> But I only want at most 2 cars with the same engine type (DIESEL, UNLEADED, HYBRID, ELECTRIC).

Your assignment is to implement a piece of code that will satisfy your customer's demands for today only.
Note that many parts of this assignment are intentionally oversimplified.

You have the following limitations:
- implement the CarShopper interface as provided - without changing it
- adhere to the car shop API as defined via [openapi.yaml](car-shop-api-client/openapi/openapi.yaml), 
- do not spend more than 1 hour on this (you can invest more, but please be honest and inform us)

Other than that feel free to:
- use any library or client of your choice, you don't have to use the provided ones
- write any tests you prefer (feel free to use the provided test or test data as you see fit or not at all)
- restructure the project as you see fit, but keep in mind that it is not part of the assignment

### Prepared Project

This project contains a prepared implementation of a client application without the required business logic implemented.
You can use it to help you with implementation.
The project consists of two submodules.
- **car-shop-api-client** - contains OpenAPI definition of car shop API to be used in the assignment and
  plugin to generate Spring WebClient API Client to help you with the implementation of the assignment.
- **car-shopper** - project with dummy client using car shop API (see *DummyCarShopper* class).
  You should create your own CarShopper implementation that will fulfill the assignment.
  You can enhance the prepared test/ add more to cover the newly implemented solution.
  
## Getting Started

Provided project is Spring Boot 3.0 application in Java 17 built by Gradle.
To build and execute tests install Java 17+ and then run:
```shell
./gradlew clean test --info
```

### How to Submit the Assignment

 - fork the repository 
 - create a branch identified by your name
 - implement the assignment
 - create a Pull Request to the main branch of original repository

## Authors

[Eleveo Technical Experts Group](mailto:teg@eleveo.com)

## License

Copyright © Eleveo a.s.
