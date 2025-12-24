# JVM Basics

## 1. JDK, JRE, and JVM

### JDK (Java Development Kit)
The JDK is a software development environment used for developing Java applications. It includes:
- **JRE**: To run Java programs.
- **Compilers and Tools**: `javac` (compiler), `jdb` (debugger), etc.
*Think of it as the complete toolkit for a Java developer.*

### JRE (Java Runtime Environment)
The JRE is the part of the JDK that actually runs Java programs. It includes:
- **JVM**: The engine that executes the code.
- **Core Libraries**: Essential Java classes (like `String`, `System`, etc.).
*Think of it as the environment where the Java code lives and runs.*

### JVM (Java Virtual Machine)
The JVM is the engine that drives the Java code. It converts Java bytecode into machine language that the computer's processor can understand.
*Think of it as the translator that speaks both "Java Bytecode" and "Hardware Machine Code".*

## 2. Bytecode
When you compile a Java file (`.java`) using `javac`, it is transformed into a `.class` file. This `.class` file contains **Bytecode**.
Bytecode is a set of instructions that the JVM understands. It is not readable by humans (easily) and is not native machine code for your storage. It is "intermediate" code.

## 3. "Write Once, Run Anywhere" (WORA)
This is Java's famous slogan.
- You write the code once in `.java` files.
- You compile it once into `.class` files (Bytecode).
- You can run this SAME `.class` file on Windows, Mac, Linux, or any device that has a JVM.
- The JVM on Windows translates bytecode to Windows commands. The JVM on Mac translates it to Mac commands.
- You don't need to rewrite or recompile your code for different operating system!
