package com.vinn.springgenie;

/**
 * A helper class to generate the content for the Maven `pom.xml` file.
 * This includes basic project information and Spring Boot starter dependencies.
 */
public class PomXmlGenerator {

    /**
     * Generates the `pom.xml` content for a Spring Boot project,
     * including the appropriate database driver dependency.
     *
     * @param projectName The name of the project.
     * @param basePackage The base package for the project's artifacts.
     * @param databaseType The selected database type (h2, mysql, postgresql).
     * @return A string containing the `pom.xml` content.
     */
    public static String generate(final String projectName, final String basePackage, final String databaseType) {
        StringBuilder dependencies = new StringBuilder();

        dependencies.append("""
                        <dependency>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-starter-web</artifactId>
                        </dependency>
                        <dependency>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-starter-data-jpa</artifactId>
                        </dependency>
                """);

        switch (databaseType.toLowerCase()) {
            case "h2":
                dependencies.append("""
                        <dependency>
                            <groupId>com.h2database</groupId>
                            <artifactId>h2</artifactId>
                            <scope>runtime</scope>
                        </dependency>
                        """);
                break;
            case "mysql":
                dependencies.append("""
                        <dependency>
                            <groupId>mysql</groupId>
                            <artifactId>mysql-connector-java</artifactId>
                            <version>8.0.33</version> <!-- Use a compatible version -->
                            <scope>runtime</scope>
                        </dependency>
                        """);
                break;
            case "postgresql":
                dependencies.append("""
                        <dependency>
                            <groupId>org.postgresql</groupId>
                            <artifactId>postgresql</artifactId>
                            <scope>runtime</scope>
                        </dependency>
                        """);
                break;
            default:
                System.err.println("Warning: Unknown database type specified. No specific database dependency added.");
                dependencies.append("""
                        <dependency>
                            <groupId>com.h2database</groupId>
                            <artifactId>h2</artifactId>
                            <scope>runtime</scope>
                        </dependency>
                        """);
                break;
        }

        dependencies.append("""
                        <dependency>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <optional>true</optional>
                        </dependency>
                        <dependency>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-starter-test</artifactId>
                            <scope>test</scope>
                        </dependency>
                """);

        dependencies.append("""
                        <dependency>
                            <groupId>org.springdoc</groupId>
                            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
                            <version>2.3.0</version>
                        </dependency>
                """);

        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                    <modelVersion>4.0.0</modelVersion>
                    <parent>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter-parent</artifactId>
                        <version>3.2.5</version> <!-- Use a recent stable Spring Boot version -->
                        <relativePath/> <!-- lookup parent from repository -->
                    </parent>
                    <groupId>%s</groupId>
                    <artifactId>%s</artifactId>
                    <version>0.0.1-SNAPSHOT</version>
                    <name>%s</name>
                    <description>Demo project for Spring Boot Module: %s</description>
                    <properties>
                        <java.version>17</java.version> <!-- Recommended Java version for Spring Boot 3 -->
                    </properties>
                    <dependencies>
                        %s
                    </dependencies>

                    <build>
                        <plugins>
                            <plugin>
                                <groupId>org.springframework.boot</groupId>
                                <artifactId>spring-boot-maven-plugin</artifactId>
                                <configuration>
                                    <excludes>
                                        <exclude>
                                            <groupId>org.projectlombok</groupId>
                                            <artifactId>lombok</artifactId>
                                        </exclude>
                                    </excludes>
                                </configuration>
                            </plugin>
                        </plugins>
                    </build>

                </project>
                """.formatted(basePackage, projectName, projectName, projectName, dependencies.toString());
    }
}