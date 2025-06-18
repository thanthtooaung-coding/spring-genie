package com.vinn.springgenie;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * A Command Line Interface (CLI) tool for generating a basic Spring Boot
 * project with a three-layer architecture: Presentation (Controller),
 * Business (Service), and Data Access (Repository, Entity).
 *
 * This tool prompts the user for a project name, a base package, and a module name,
 * then generates the corresponding Java source files and a Maven pom.xml.
 */
public class CliGenerator {

    private static final String SRC_MAIN_JAVA = "src/main/java";

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Spring Boot Three-Layer Architecture Generator");
        System.out.println("----------------------------------------------");

        System.out.print("Enter Project Name (e.g., my-app): ");
        String projectName = scanner.nextLine();

        System.out.print("Enter Base Package (e.g., com.example.myapp): ");
        String basePackage = scanner.nextLine().toLowerCase();

        System.out.print("Enter Module Name (e.g., Product, User - singular, PascalCase): ");
        String moduleName = scanner.nextLine();

        scanner.close();

        final String pascalCaseModuleName = toPascalCase(moduleName);
        final String camelCaseModuleName = toCamelCase(moduleName);

        System.out.println("\nGenerating project structure for module: " + pascalCaseModuleName + "...");

        try {
            // Create root project directory
            final Path projectRootPath = Paths.get(projectName);
            Files.createDirectories(projectRootPath);

            // Generate pom.xml
            generatePomXml(projectRootPath, projectName, basePackage);

            // Create Java source directories
            final Path javaBasePath = projectRootPath.resolve(SRC_MAIN_JAVA).resolve(basePackage.replace(".", File.separator));
            final Path moduleBasePath = javaBasePath.resolve(camelCaseModuleName);

            Files.createDirectories(moduleBasePath.resolve("controller"));
            Files.createDirectories(moduleBasePath.resolve("service"));
            Files.createDirectories(moduleBasePath.resolve("repository"));
            Files.createDirectories(moduleBasePath.resolve("entity"));

            // Generate Java files
            generateApplicationClass(basePackage, pascalCaseModuleName, moduleBasePath);
            generateEntityClass(basePackage, pascalCaseModuleName, moduleBasePath);
            generateRepositoryClass(basePackage, pascalCaseModuleName, moduleBasePath);
            generateServiceClass(basePackage, pascalCaseModuleName, moduleBasePath);
            generateControllerClass(basePackage, pascalCaseModuleName, moduleBasePath);

            System.out.println("\nProject '" + projectName + "' generated successfully!");
            System.out.println("Navigate to the project directory: cd " + projectName);
            System.out.println("Then you can build and run it using Maven: mvn spring-boot:run");

        } catch (IOException e) {
            System.err.println("Error generating project: " + e.getMessage());
        }
    }

    /**
     * Converts a string to PascalCase (e.g., "product-item" -> "ProductItem").
     *
     * @param input The input string.
     * @return The PascalCase string.
     */
    private static String toPascalCase(final String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        final StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;
        for (char c : input.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            } else {
                capitalizeNext = true;
            }
        }
        return result.toString();
    }

    /**
     * Converts a string to camelCase (e.g., "ProductItem" -> "productItem").
     *
     * @param input The input string.
     * @return The camelCase string.
     */
    private static String toCamelCase(final String input) {
        final String pascalCase = toPascalCase(input);
        if (pascalCase.isEmpty()) {
            return "";
        }
        return Character.toLowerCase(pascalCase.charAt(0)) + pascalCase.substring(1);
    }

    /**
     * Writes content to a file.
     *
     * @param path    The path to the file.
     * @param content The content to write.
     * @throws IOException If an I/O error occurs.
     */
    private static void writeFile(final Path path, String content) throws IOException {
        try (final FileWriter writer = new FileWriter(path.toFile())) {
            writer.write(content);
            System.out.println("Generated: " + path.getFileName());
        }
    }

    /**
     * Generates the pom.xml file.
     *
     * @param projectRootPath The root path of the project.
     * @param projectName     The name of the project.
     * @param basePackage     The base package of the project.
     * @throws IOException If an I/O error occurs.
     */
    private static void generatePomXml(final Path projectRootPath, final  String projectName, final  String basePackage) throws IOException {
        final Path pomPath = projectRootPath.resolve("pom.xml");
        final String content = PomXmlGenerator.generate(projectName, basePackage);
        writeFile(pomPath, content);
    }

    /**
     * Generates the main Spring Boot Application class.
     *
     * @param basePackage          The base package.
     * @param pascalCaseModuleName The module name in PascalCase.
     * @param moduleBasePath       The base path for the module's Java files.
     * @throws IOException If an I/O error occurs.
     */
    private static void generateApplicationClass(final String basePackage, final  String pascalCaseModuleName, final  Path moduleBasePath) throws IOException {
        final Path filePath = moduleBasePath.resolve("Application.java");
        final String content = ApplicationClassGenerator.generate(basePackage, pascalCaseModuleName);
        writeFile(filePath, content);
    }

    /**
     * Generates the Entity class.
     *
     * @param basePackage          The base package.
     * @param pascalCaseModuleName The module name in PascalCase.
     * @param moduleBasePath       The base path for the module's Java files.
     * @throws IOException If an I/O error occurs.
     */
    private static void generateEntityClass(final String basePackage, final  String pascalCaseModuleName, final  Path moduleBasePath) throws IOException {
        final Path filePath = moduleBasePath.resolve("entity").resolve(pascalCaseModuleName + ".java");
        final String content = EntityClassGenerator.generate(basePackage, pascalCaseModuleName);
        writeFile(filePath, content);
    }

    /**
     * Generates the Repository interface.
     *
     * @param basePackage          The base package.
     * @param pascalCaseModuleName The module name in PascalCase.
     * @param moduleBasePath       The base path for the module's Java files.
     * @throws IOException If an I/O error occurs.
     */
    private static void generateRepositoryClass(final String basePackage, final  String pascalCaseModuleName, final  Path moduleBasePath) throws IOException {
        final Path filePath = moduleBasePath.resolve("repository").resolve(pascalCaseModuleName + "Repository.java");
        final String content = RepositoryClassGenerator.generate(basePackage, pascalCaseModuleName);
        writeFile(filePath, content);
    }

    /**
     * Generates the Service class.
     *
     * @param basePackage          The base package.
     * @param pascalCaseModuleName The module name in PascalCase.
     * @param moduleBasePath       The base path for the module's Java files.
     * @throws IOException If an I/O error occurs.
     */
    private static void generateServiceClass(final String basePackage, final  String pascalCaseModuleName, final  Path moduleBasePath) throws IOException {
        final Path filePath = moduleBasePath.resolve("service").resolve(pascalCaseModuleName + "Service.java");
        final String content = ServiceClassGenerator.generate(basePackage, pascalCaseModuleName);
        writeFile(filePath, content);
    }

    /**
     * Generates the Controller class.
     *
     * @param basePackage          The base package.
     * @param pascalCaseModuleName The module name in PascalCase.
     * @param moduleBasePath       The base path for the module's Java files.
     * @throws IOException If an I/O error occurs.
     */
    private static void generateControllerClass(final String basePackage, final  String pascalCaseModuleName, final  Path moduleBasePath) throws IOException {
        final Path filePath = moduleBasePath.resolve("controller").resolve(pascalCaseModuleName + "Controller.java");
        final String content = ControllerClassGenerator.generate(basePackage, pascalCaseModuleName);
        writeFile(filePath, content);
    }
}