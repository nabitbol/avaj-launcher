FROM openjdk:24-oraclelinux8
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp/src
RUN javac Simulation.java
ENTRYPOINT ["java"]
CMD ["Simulation",  "../scenario.txt"]