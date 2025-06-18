package com.vinn.springgenie;

/**
 * A helper class to generate the content for a JPA Entity class.
 * This class represents a table in the database and defines its structure.
 */
public class EntityClassGenerator {

    /**
     * Generates the content for a JPA Entity class.
     * It includes basic ID, name, and description fields,
     * along with Lombok annotations for boilerplate code (getters, setters, constructors).
     *
     * @param basePackage          The base package of the application.
     * @param pascalCaseModuleName The module name in PascalCase (e.g., "Product").
     * @return A string containing the Entity class content.
     */
    public static String generate(final String basePackage, final String pascalCaseModuleName) {
        final String camelCaseModuleName = Character.toLowerCase(pascalCaseModuleName.charAt(0)) + pascalCaseModuleName.substring(1);
        return """
                package %s.%s.entity;

                import jakarta.persistence.Entity;
                import jakarta.persistence.GeneratedValue;
                import jakarta.persistence.GenerationType;
                import jakarta.persistence.Id;
                import lombok.AllArgsConstructor;
                import lombok.Data;
                import lombok.NoArgsConstructor;

                /**
                 * Represents the %s entity in the database.
                 * This class is mapped to a database table and defines the schema for %s data.
                 */
                @Entity
                @Data // Lombok: Generates getters, setters, toString, equals, and hashCode methods
                @NoArgsConstructor // Lombok: Generates a no-argument constructor
                @AllArgsConstructor // Lombok: Generates a constructor with all fields
                public class %s {

                    @Id
                    @GeneratedValue(strategy = GenerationType.IDENTITY)
                    private Long id;

                    private String name;
                    private String description;

                    // Additional fields can be added here based on specific requirements

                    /**
                     * Constructor for creating a new %s without an ID (for persistence).
                     *
                     * @param name The name of the %s.
                     * @param description A brief description of the %s.
                     */
                    public %s(String name, String description) {
                        this.name = name;
                        this.description = description;
                    }
                }
                """.formatted(basePackage, camelCaseModuleName, pascalCaseModuleName, pascalCaseModuleName, pascalCaseModuleName, pascalCaseModuleName, pascalCaseModuleName, pascalCaseModuleName, pascalCaseModuleName);
    }
}