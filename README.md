# Car Shop Assignment

Assignment for Java Backend Engineer candidates in Eleveo.

## Description

This project contains assignment description and definition of API needed to fulfill the assignment.
Also prepared example client implementation is provided.

### Assignment

You have a rich client and he asked you to fill his garage with new cars. Today he wants to buy 5 new cars.
He described his preferences as following:

> I don`t care about car price, I only want to buy the most powerful cars (most horsepowers),
> but I only want at most 2 cars with the same engine type (DIESEL, UNLEADED, HYBRID, ELECTRIC).

There is car shop API provided to you (see *openapi.yaml*), that allows you to get list of available cars and order them.
Write the program that will satisfy your customer demands for today. There is a test with mock responses prepared for you, so you can use it to test your code.
You do not have to use what you have provided, you can use any framework of your choice. But you cannot change car shop API in any way.
You should spend with the assignment approximately 1 hour. You can invest into it more,
but then please be honest and inform us about it.

### Prepared Project

This project contains prepared implementation of client application without required assignment logic implemented.
You can use it to help you with implementation.

Project consists of two submodules.
- **car-shop-api-client** - contains OpenAPI definition of car shop API to be used in assignment and
  plugin to generate Spring WebClient API Client to help you with implementation of assignment.
- **car-shopper** - project with dummy client using car shop API (see *DummyCarShopper* class).
  You should implement your own CarShopper interface implementation that will fulfill the assignment.
  You can enhance prepared test to test your implementation. 

## Getting Started

Provided project is Spring Boot 3.0 application in Java 17 build by Gradle.

To build and execute tests just run:
```shell
./gradlew clean test --info
```

## Authors

[Eleveo Technical Experts Group](teg@eleveo.com)

## Version History

* 0.0.1
    * Initial Release

## License

Copyright © Eleveo a.s.
