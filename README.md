# avaj-launcher
Java-based aircraft simulation program that models weather effects on different aircraft types.

# Tools
- Java version 24

# Quick start
```bash
$> git clone [https://github.com/nabitbol/avaj-launcher.git](https://github.com/nabitbol/avaj-launcher.git)
$> cd src
$> javac Simulation.java
$> java Simulation
```
# Need to know

This project aims to teach the basics of the Java programming language. To make this project, only the standard library is allowed; no code generation tools such as protoc for Protocol Buffers or package managing tools like Maven or Gradle aren't allowed.

Also, this project requests to build two classes following the singleton pattern, which nowadays is considered an anti-pattern and its usage has started to decline in Java community. To learn more about ['Singleton as an anti-pattern click here.'](https://www.michaelsafyan.com/tech/design/patterns/singleton)

# Roadmap

## Mandatory
- [ ] Implement data structures according to the UML diagram provided by the subject. **WIP**
- [ ] Implement the simulation algorithm.
- [ ] Parse the input file describing the simulation:
    - [ ] The number of iterations.
    - [ ] The type of aircraft and their coordinates.
- [ ] Package the project. **WIP**

## Bonus
- [x] Create your own custom exceptions for treating abnormal behavior.
My bonus
- [ ] Incorporate a Makefile to build the project.
- [ ] Parallelize the simulation:
    - [ ] Add thread safety to the singletons.
    - [ ] Get weather from the OpenWeatherMap API.
