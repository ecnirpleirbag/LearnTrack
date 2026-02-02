# JVM Basics

## JDK, JRE, and JVM
- **JDK (Java Development Kit)**: The complete toolkit for developing Java applications. It includes JRE and development tools like `javac`.
- **JRE (Java Runtime Environment)**: The environment needed to run Java applications. It includes the JVM and core libraries.
- **JVM (Java Virtual Machine)**: The engine that actually runs the Java bytecode.

## Bytecode
Bytecode is the intermediate representation of Java code. When you compile `.java` files with `javac`, it produces `.class` files containing bytecode. This is what the JVM executes.

## Write Once, Run Anywhere (WORA)
Java's "Write Once, Run Anywhere" philosophy means that code compiled on one platform (e.g., Windows) can run on any other platform (e.g., Linux, macOS) without modification, as long as that platform has a compatible JVM installed. The JVM acts as an abstraction layer between the bytecode and the underlying operating system.
