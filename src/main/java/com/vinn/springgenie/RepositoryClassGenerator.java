package com.vinn.springgenie;

/**
 * A helper class to generate the content for a Spring Data JPA Repository interface.
 * This interface provides methods for CRUD operations on the corresponding entity.
 */
public class RepositoryClassGenerator {

    /**
     * Generates the content for a Spring Data JPA Repository interface.
     * It extends JpaRepository to inherit common CRUD functionalities.
     *
     * @param basePackage          The base package of the application.
     * @param pascalCaseModuleName The module name in PascalCase (e.g., "Product").
     * @return A string containing the Repository interface content.
     */
    public static String generate(final String basePackage, final String pascalCaseModuleName) {
        final String camelCaseModuleName = Character.toLowerCase(pascalCaseModuleName.charAt(0)) + pascalCaseModuleName.substring(1);
        return """
                package %s.%s.repository;

                import %s.%s.entity.%s;
                import org.springframework.data.jpa.repository.JpaRepository;
                import org.springframework.stereotype.Repository;

                /**
                 * Spring Data JPA repository for the %s entity.
                 * Provides standard CRUD operations and custom query capabilities for %s data.
                 */
                @Repository
                public interface %sRepository extends JpaRepository<%s, Long> {
                    // Custom query methods can be added here if needed, e.g.:
                    // Optional<%s> findByName(String name);
                }
                """.formatted(basePackage, camelCaseModuleName, basePackage, camelCaseModuleName, pascalCaseModuleName, pascalCaseModuleName, pascalCaseModuleName, pascalCaseModuleName, pascalCaseModuleName, pascalCaseModuleName);
    }
}