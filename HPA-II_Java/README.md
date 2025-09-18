# HPA-II: High Performance Applications II (Java)

## Overview
This repository contains comprehensive learning materials for High Performance Applications II using Java, organized into three main categories: Theory, Projects, and Practices. All content is designed to be cross-platform compatible with Linux, Windows, and macOS.

## Repository Structure

```
HPA-II_Java/
├── Theory/           # Theoretical concepts and explanations
├── Projects/         # Project specifications and requirements
├── Practices/        # Hands-on exercises and solutions
└── README.md        # This file
```

## Theory
Comprehensive theoretical explanations with examples covering:

- **01-java-basics.md**: Java basics and program structure
- **02-object-oriented-programming.md**: OOP principles in Java
- **03-collections-framework.md**: Java Collections Framework
- **04-exception-handling.md**: Exception handling in Java

## Projects
Detailed project specifications for practical application:

- **01-banking-system.md**: Banking system with account management
- **02-inventory-management-system.md**: Inventory management with stock tracking
- **03-student-grade-management.md**: Student grade management system

## Practices
Hands-on exercises with complete solutions:

- **01-hello-world/**: Hello World and basic program structure
- **02-variables-types/**: Variables and data types
- **03-input-output/**: Input/output operations
- **04-conditionals/**: Conditional statements
- **05-loops/**: Loops and iteration
- **06-arrays/**: Arrays and collections
- **07-methods/**: Methods and functions
- **08-classes-objects/**: Classes and objects
- **09-inheritance/**: Inheritance and polymorphism
- **10-exception-handling/**: Exception handling
- **11-collections/**: Collections framework

## Cross-Platform Compatibility

All code examples and projects are designed to work on:

- **Linux**: Using OpenJDK or Oracle JDK
- **Windows**: Using OpenJDK, Oracle JDK, or Microsoft OpenJDK
- **macOS**: Using OpenJDK or Oracle JDK

### Compilation Examples

#### Linux/macOS
```bash
javac *.java
java ClassName
```

#### Windows (Command Prompt)
```cmd
javac *.java
java ClassName
```

#### Windows (PowerShell)
```powershell
javac *.java
java ClassName
```

## Prerequisites

- Java 11 or higher (JDK recommended)
- Text editor or IDE (IntelliJ IDEA, Eclipse, VS Code)
- Basic understanding of programming concepts
- Command line/terminal access

## Learning Path

1. **Start with Theory**: Read through the theoretical concepts in the Theory directory
2. **Practice with Exercises**: Work through the practices in the Practices directory
3. **Apply with Projects**: Implement the projects in the Projects directory
4. **Cross-Platform Testing**: Test your code on different platforms

## Java Environment Setup

### Linux (Ubuntu/Debian)
```bash
# Install OpenJDK 11
sudo apt update
sudo apt install openjdk-11-jdk

# Verify installation
java -version
javac -version

# Set JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export PATH=$PATH:$JAVA_HOME/bin
```

### Linux (CentOS/RHEL/Fedora)
```bash
# Install OpenJDK 11
sudo yum install java-11-openjdk-devel
# or for newer versions
sudo dnf install java-11-openjdk-devel

# Verify installation
java -version
javac -version
```

### Windows
1. Download and install OpenJDK or Oracle JDK from:
   - [OpenJDK](https://openjdk.java.net/)
   - [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Set environment variables:
   ```cmd
   set JAVA_HOME=C:\Program Files\Java\jdk-11
   set PATH=%PATH%;%JAVA_HOME%\bin
   ```
3. Verify installation in Command Prompt:
   ```cmd
   java -version
   javac -version
   ```

### macOS
```bash
# Using Homebrew
brew install openjdk@11

# Set JAVA_HOME
export JAVA_HOME=/opt/homebrew/opt/openjdk@11/libexec/openjdk.jdk/Contents/Home
export PATH=$PATH:$JAVA_HOME/bin

# Verify installation
java -version
javac -version
```

## Features

- **Comprehensive Coverage**: From basic Java syntax to advanced OOP concepts
- **Cross-Platform**: Works on Linux, Windows, and macOS
- **Practical Examples**: Real-world applicable code examples
- **Progressive Difficulty**: From beginner to intermediate level
- **Complete Solutions**: All practices include complete working solutions
- **Project-Based Learning**: Hands-on projects for practical experience

## Getting Started

1. **Setup Java Environment**: Install JDK 11 or higher on your system
2. **Choose a Topic**: Start with Theory directory to understand concepts
3. **Practice**: Work through exercises in the Practices directory
4. **Build Projects**: Implement projects from the Projects directory
5. **Test Cross-Platform**: Ensure your code works on different platforms

## IDE Recommendations

### IntelliJ IDEA Community Edition
- Free and feature-rich
- Excellent Java support
- Built-in debugging and testing tools
- Cross-platform compatibility

### Eclipse IDE
- Free and open-source
- Extensive plugin ecosystem
- Good for Java development
- Cross-platform support

### Visual Studio Code
- Free and lightweight
- Excellent Java extension pack
- Great for learning and development
- Cross-platform compatibility

### NetBeans
- Free and open-source
- Good Java support
- Built-in GUI builder
- Cross-platform support

## Build Tools (Optional)

### Maven
```xml
<!-- Example pom.xml for Maven projects -->
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>my-project</artifactId>
    <version>1.0.0</version>
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>
</project>
```

### Gradle
```gradle
// Example build.gradle for Gradle projects
plugins {
    id 'java'
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
```

## Testing

### JUnit 5 Setup
```xml
<!-- Maven dependency -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
</dependency>
```

### Running Tests
```bash
# Maven
mvn test

# Gradle
gradle test

# Command line
javac -cp .:junit-platform-console-standalone.jar *.java
java -cp .:junit-platform-console-standalone.jar org.junit.platform.console.ConsoleLauncher --scan-classpath
```

## Common Issues and Solutions

### "javac is not recognized"
- **Windows**: Add Java bin directory to PATH environment variable
- **Linux/macOS**: Install JDK (not just JRE) and set JAVA_HOME

### "Could not find or load main class"
- Ensure .class files are in the correct directory
- Check class name matches file name
- Verify package declarations if using packages

### "ClassNotFoundException"
- Check classpath settings
- Ensure all required JAR files are included
- Verify class names and package structure

## Contributing

This repository is designed for educational purposes. Feel free to:
- Add more examples and exercises
- Improve existing code and documentation
- Add cross-platform compatibility notes
- Suggest new topics or projects
- Report issues and bugs

## Resources

### Official Documentation
- [Oracle Java Documentation](https://docs.oracle.com/en/java/)
- [Java Tutorial](https://docs.oracle.com/javase/tutorial/)
- [Java API Reference](https://docs.oracle.com/en/java/javase/11/docs/api/)

### Learning Resources
- [Java Code Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html)
- [Effective Java by Joshua Bloch](https://www.oracle.com/technical-resources/articles/java/effective-java.html)
- [Java Best Practices](https://github.com/alibaba/p3c)

### Community
- [Stack Overflow](https://stackoverflow.com/questions/tagged/java)
- [Reddit r/java](https://www.reddit.com/r/java/)
- [Oracle Java Community](https://community.oracle.com/community/java)

## License

This educational content is provided for learning purposes. Please respect the educational nature of this repository.

## Contact

For questions or suggestions regarding this educational content, please refer to the course materials or instructor.

---

**Happy Learning with Java!** 🚀☕
