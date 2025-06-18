package com.vinn.springgenie;

/**
 * A helper class to generate the content for the Gradle `build.gradle` file.
 * This includes basic project information and Spring Boot starter dependencies.
 */
public class GradleBuildFileGenerator {

    /**
     * Generates the `build.gradle` content for a Spring Boot project,
     * including the appropriate database driver dependency.
     *
     * @param projectName The name of the project.
     * @param basePackage The base package for the project's artifacts (used for group ID).
     * @param databaseType The selected database type (h2, mysql, postgresql).
     * @return A string containing the `build.gradle` content.
     */
    public static String generate(final String projectName, final String basePackage, final String databaseType) {
        StringBuilder dependencies = new StringBuilder();
        String mainClassName = basePackage + ".Application";

        // Common Spring Boot Web and JPA starters
        dependencies.append("""
            implementation 'org.springframework.boot:spring-boot-starter-web'
            implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
            """);

        // Add database specific dependency
        switch (databaseType.toLowerCase()) {
            case "h2":
                dependencies.append("    runtimeOnly 'com.h2database:h2'\n");
                break;
            case "mysql":
                dependencies.append("    runtimeOnly 'mysql:mysql-connector-java:8.0.33'\n");
                break;
            case "postgresql":
                dependencies.append("    runtimeOnly 'org.postgresql:postgresql'\n");
                break;
            default:
                System.err.println("Warning: Unknown database type specified. No specific database dependency added.");
                dependencies.append("    runtimeOnly 'com.h2database:h2'\n");
                break;
        }

        // Add Lombok and Test dependencies
        dependencies.append("""
            compileOnly 'org.projectlombok:lombok'
            annotationProcessor 'org.projectlombok:lombok'
            testImplementation 'org.springframework.boot:spring-boot-starter-test'
            """);


        return """
                plugins {
                    id 'java'
                    id 'org.springframework.boot' version '3.2.5' // Use a recent stable Spring Boot version
                    id 'io.spring.dependency-management' version '1.1.4'
                }

                group = '%s'
                version = '0.0.1-SNAPSHOT'
                sourceCompatibility = '17' // Recommended Java version for Spring Boot 3

                configurations {
                    compileOnly {
                        extendsFrom annotationProcessor
                    }
                }

                repositories {
                    mavenCentral()
                }

                dependencies {
                %s
                }

                tasks.named('test') {
                    useJUnitPlatform()
                }

                // Configuration for Spring Boot's 'bootJar' task to create an executable JAR
                bootJar {
                    archiveFileName = '%s.jar'
                    mainClass = '%s'
                }
                """.formatted(basePackage, dependencies.toString(), projectName, mainClassName);
    }
}