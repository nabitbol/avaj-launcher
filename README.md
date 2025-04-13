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
# Good to know

This project aims to teach the basics of the Java programming language. To make this project, only the standard library is allowed; no code generation tools such as protoc for Protocol Buffers or package managing tools like Maven or Gradle aren't allowed.

Also, this project requests to build two classes following the singleton pattern, which nowadays is considered an anti-pattern and its usage has started to decline in Java community. To learn more about ['Singleton as an anti-pattern click here.'](https://www.michaelsafyan.com/tech/design/patterns/singleton).

Following my understanding, the `Tower`, `WeatherTower`, and `Flyable` aircraft try to follow the Observer design pattern. We can deduce this based on this information:
-  In `Tower`, the `Flyable` list is named **observers**.
-  `Tower` exposes a `conditionChanged` method that should notify the `Flyable` observers.
-  This method is `protected`, which allows `WeatherTower` to use it.

The UML diagram specifies that the `Flyable`'s `observers` list within the `Tower` class should be private. This creates a challenge: `WeatherTower` is responsible for accessing weather data, but with `private` observers, it cannot directly provide this data to the `Flyable` objects. To address this, the design includes a `public` `getWeather` method in `WeatherTower`, allowing each `Flyable` to request weather information relevant to its position by calling this method within its `updateCondition` method. This raises a design question: Should the `Flyable` objects be responsible for fetching weather data, or should the `WeatherTower` supply the weather information during each weather change notification?

# Roadmap

## Mandatory
- [ ] Implement data structures according to the UML diagram provided by the subject. **WIP**
- [x] Implement the simulation algorithm.
- [ ] Parse the input file describing the simulation:
    - [ ] The number of iterations.
    - [ ] The type of aircraft and their coordinates.
- [x] Package the project.

## Bonus
- [x] Create your own custom exceptions for treating abnormal behavior.
My bonus
- [ ] Incorporate a Makefile to build the project.
- [ ] Parallelize the simulation:
    - [ ] Add thread safety to the singletons.
- [ ] Get weather from the OpenWeatherMap API.
