# 🧞‍♂️ Spring Genie

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)

A powerful Command Line Interface (CLI) tool that generates Spring Boot projects with a clean three-layer architecture in seconds. Spring Genie eliminates the boilerplate setup and gets you coding faster with best practices built-in.

## ✨ Features

- 🏗️ **Three-Layer Architecture**: Automatically generates Controller, Service, Repository, and Entity layers
- 🛠️ **Multiple Build Tools**: Support for both Maven and Gradle
- 🗄️ **Database Flexibility**: Choose from H2, MySQL, or PostgreSQL with auto-configuration
- ⚙️ **Configuration Options**: Generate either `.properties` or `.yml` configuration files
- 📁 **Clean Project Structure**: Follows Spring Boot best practices and conventions
- 🚀 **Ready to Run**: Generated projects are immediately executable
- 🎯 **Customizable**: Specify your own package names, module names, and database settings

## 🚀 Quick Start

### Prerequisites

- Java 17 or higher
- Maven or Gradle (for running the generated project)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/thanthtooaung-coding/spring-genie.git
cd spring-genie
```

2. Build the project:
```bash
# Using Gradle
./gradlew build

# Using Maven
mvn clean compile
```

3. Run Spring Genie:
```bash
# Using Gradle
./gradlew run

# Using Maven
mvn exec:java -Dexec.mainClass="com.vinn.springgenie.CliGenerator"

# Or run the compiled class directly
java -cp target/classes com.vinn.springgenie.CliGenerator
```

## 📖 Usage

When you run Spring Genie, you'll be prompted to provide the following information:

### Basic Project Information
- **Project Name**: The name of your project (e.g., `my-awesome-app`)
- **Base Package**: Your Java package structure (e.g., `com.example.myapp`)
- **Module Name**: The main entity/module name (e.g., `Product`, `User`)

### Build Configuration
- **Build Tool**: Choose between `maven` or `gradle` (default: maven)
- **Config File Type**: Choose between `properties` or `yml` (default: properties)

### Database Configuration
- **Database Type**: Choose from:
  - `h2` - In-memory database (default, perfect for development)
  - `mysql` - MySQL database
  - `postgresql` - PostgreSQL database

For MySQL and PostgreSQL, you can also specify:
- Database name
- Auto-creation of database
- Username and password
- Custom Hibernate dialect

### Example Session

```
Spring Boot Three-Layer Architecture Generator
----------------------------------------------
Enter Project Name (e.g., my-app): task-manager
Enter Base Package (e.g., com.example.myapp): com.company.taskmanager
Enter Module Name (e.g., Product, User - singular, PascalCase): Task
Choose Build Tool (maven/gradle) [default: maven]: maven
Choose config file type (properties/yml) [default: properties]: yml
Choose Database Type (h2/mysql/postgresql) [default: h2]: mysql
Enter Database Name (e.g., mydb): taskdb
Do you want Spring Boot to create the database if it doesn't exist? (yes/no) [default: no]: yes
Enter Database Username [optional, default varies]: admin
Enter Database Password [optional, default varies]: password123

Generating project structure for module: Task...
Generated: pom.xml
Generated: Application.java
Generated: OpenApiConfig.java
Generated: Task.java
Generated: TaskRepository.java
Generated: TaskService.java
Generated: TaskController.java
Generated: application.yml

Project 'task-manager' generated successfully!
Navigate to the project directory: cd task-manager
Then you can build and run it using Maven: mvn spring-boot:run
```

## 📁 Generated Project Structure

Spring Genie creates a well-organized project structure:

```
your-project/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/yourapp/
│       │       └── yourmodule/
│       │           ├── Application.java          # Main Spring Boot application
│       │           ├── config/
│       │           │   └── OpenApiConfig.java
│       │           ├── controller/
│       │           │   └── YourModuleController.java
│       │           ├── service/
│       │           │   └── YourModuleService.java
│       │           ├── repository/
│       │           │   └── YourModuleRepository.java
│       │           └── entity/
│       │               └── YourModule.java
│       └── resources/
│           └── application.properties (or .yml)
├── pom.xml (or build.gradle)
└── README.md
```

## 🏗️ Architecture Overview

The generated projects follow a clean three-layer architecture:

### 1. **Presentation Layer (Controller)**
- Handles HTTP requests and responses
- Input validation and error handling
- RESTful API endpoints

### 2. **Business Layer (Service)**
- Contains business logic and rules
- Orchestrates data operations
- Transaction management

### 3. **Data Access Layer (Repository + Entity)**
- **Entity**: JPA entities representing database tables
- **Repository**: Data access interfaces extending JpaRepository

## 🛠️ Customization

### Database Configuration Examples

#### H2 (In-Memory)
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
  h2:
    console:
      enabled: true
```

#### MySQL
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/yourdb?createDatabaseIfNotExist=true
    username: your-username
    password: your-password
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    database-platform: org.hibernate.dialect.MySQLDialect
```

#### PostgreSQL
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/yourdb
    username: your-username
    password: your-password
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

## 🚀 Running Your Generated Project

After generation, navigate to your project directory and run:

```bash
# For Maven projects
cd your-project-name
mvn spring-boot:run

# For Gradle projects
cd your-project-name
./gradlew bootRun
```

Your application will start on `http://localhost:8080`

## 🤝 Contributing

We welcome contributions! Here's how you can help:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add some amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Development Setup

1. Clone the repository
2. Open in your favorite IDE
3. Make sure you have Java 17+ installed
4. Run the tests: `mvn test` or `./gradlew test`

## 📝 Roadmap

- [✅] Implement Swagger UI
- [ ] Support for additional databases (MongoDB, Redis)
- [ ] Web interface for project generation
- [ ] Docker containerization support
- [ ] Integration with Spring Security
- [ ] Custom template support
- [ ] REST API documentation generation
- [ ] Unit test generation

## 🐛 Issues and Support

If you encounter any issues or have questions:

1. Check the [Issues](https://github.com/thanthtooaung-coding/spring-genie/issues) page
2. Create a new issue with detailed information
3. Include your Java version, OS, and the exact command you ran

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Spring Boot team for the amazing framework
- The Java community for continuous inspiration
- All contributors who help make Spring Genie better

---

**Made with 🔥 by [thanthtooaung-coding](https://github.com/thanthtooaung-coding)**

*Happy coding! 🚀*
