# Car Shop Assignment

Assignment for Java Backend Engineer candidates in Eleveo.

## Description

This project contains an assignment description and definition of the API needed to fulfill the assignment.
Also prepared example of client implementation is provided.

### Assignment

As a personal shopper, you have a rich client that asked you to buy him 5 new cars as an addition to his garage.
He described his preferences as follows:

> I don`t care about the car price, I only want to buy the most powerful ones (most horsepowers),
> but, I only want at most 2 cars with the same engine type (DIESEL, UNLEADED, HYBRID, ELECTRIC).

There is a car shop API provided to you (see *openapi.yaml*), that allows you to get the list of available cars and place an order.
Your mission, if you accept it, is to implement the piece of code that will satisfy your customer demands for today. There is a test with mock responses prepared for you, so you can use it to test your code.
You do not have to use what you have provided, you can use any framework of your choice. But you cannot change the car shop API in any way.
You should spend approximately 1 hour on this assignment. You can invest in it more,
but then please be honest and inform us about it.

### Prepared Project

This project contains a prepared implementation of a client application without the required assignment logic implemented.
You can use it to help you with implementation.
The project consists of two submodules.
- **car-shop-api-client** - contains OpenAPI definition of car shop API to be used in the assignment and
  plugin to generate Spring WebClient API Client to help you with the implementation of the assignment.
- **car-shopper** - project with dummy client using car shop API (see *DummyCarShopper* class).
  You should implement your own CarShopper interface that will fulfill the assignment.
  You can enhance the prepared test/ add more to cover the newly implemented solution. 
  
## Getting Started

Provided project is Spring Boot 3.0 application in Java 17 built by Gradle.
To build and execute tests just run:
```shell
./gradlew clean test --info
```

### How to Submit the Assignment

 - fork repository 
 - create branch identified by your name
 - implement the assignment
 - create Pull Request to the main branch of original repository

## Authors
[Eleveo Technical Experts Group](mailto:teg@eleveo.com)

## Version History

* 0.0.1
    * Initial Release

## License

Copyright © Eleveo a.s.
