# scalable-architecture
How to create an app that can be scaled from monolith to full blown microservice environment?


Idea:

How can we create an application that in a first phase can be deployed as a monolith, with fast in-process communication, and later on deployed as a set of MicroServices that are called over HTTP and use Queue and Topic from messaging system to have reliable communications.

Proof of Concept using

- Jakarta EE 8
- MicroProfile 4.1
- Payara Micro 5

## services directory

A multi-module project that contains all code as building blocks.

## packaging directory

A multi-module project hosting the different stages of packaging.
