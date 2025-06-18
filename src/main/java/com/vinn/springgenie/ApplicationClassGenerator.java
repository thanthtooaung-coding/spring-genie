package com.vinn.springgenie;

/**
 * A helper class to generate the content for the main Spring Boot Application class.
 * This class serves as the entry point for the Spring Boot application.
 */
public class ApplicationClassGenerator {

    /**
     * Generates the content for the main Spring Boot Application class.
     *
     * @param basePackage          The base package of the application.
     * @param pascalCaseModuleName The module name in PascalCase.
     * @return A string containing the Application class content.
     */
    public static String generate(final String basePackage, final String pascalCaseModuleName) {
        final String camelCaseModuleName = Character.toLowerCase(pascalCaseModuleName.charAt(0)) + pascalCaseModuleName.substring(1);
        return """
                package %s.%s;

                import org.springframework.boot.SpringApplication;
                import org.springframework.boot.autoconfigure.SpringBootApplication;

                /**
                 * Main entry point for the %s Spring Boot application.
                 * This class enables auto-configuration, component scanning, and serves as the
                 * starting point for running the application.
                 */
                @SpringBootApplication
                public class Application {

                    public static void main(String[] args) {
                        SpringApplication.run(Application.class, args);
                    }

                }
                """.formatted(basePackage, camelCaseModuleName, pascalCaseModuleName);
    }
}