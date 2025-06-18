package com.vinn.springgenie;

/**
 * A helper class to generate the content for a business layer Service class.
 * This class encapsulates business logic and interacts with the repository.
 * It is responsible for business logic pertaining to entities.
 */
public class ServiceClassGenerator {

    /**
     * Generates the content for a business layer Service class.
     * It includes basic CRUD operations that delegate to the repository.
     *
     * @param basePackage          The base package of the application.
     * @param pascalCaseModuleName The module name in PascalCase (e.g., "Product").
     * @return A string containing the Service class content.
     */
    public static String generate(final String basePackage, final String pascalCaseModuleName) {
        final String camelCaseModuleName = Character.toLowerCase(pascalCaseModuleName.charAt(0)) + pascalCaseModuleName.substring(1);
        final String repositoryVarName = camelCaseModuleName + "Repository";
        return """
                package %s.%s.service;

                import %s.%s.entity.%s;
                import %s.%s.repository.%sRepository;
                import org.springframework.stereotype.Service;
                import java.util.List;
                import java.util.Optional;

                /**
                 * Service layer for managing %s entities.
                 * This class contains the business logic for operations related to %s.
                 * It acts as an intermediary between the Controller and Repository layers for %s data.
                 */
                @Service
                public class %sService {

                    private final %sRepository %s;

                    /**
                     * Constructs a new %sService with the given %sRepository.
                     * Spring automatically injects the %sRepository instance.
                     *
                     * @param %s The %sRepository to be used by this service.
                     */
                    public %sService(final %sRepository %s) {
                        this.%s = %s;
                    }

                    /**
                     * Retrieves all %s entities.
                     * This method fetches all %s records from the database.
                     *
                     * @return A list of all %s entities.
                     */
                    public List<%s> findAll() {
                        return this.%s.findAll();
                    }

                    /**
                     * Retrieves a %s entity by its ID.
                     * This method attempts to find a single %s based on its primary key.
                     *
                     * @param id The ID of the %s to retrieve.
                     * @return An Optional containing the %s if found, or empty if not.
                     */
                    public Optional<%s> findById(final Long id) {
                        return this.%s.findById(id);
                    }

                    /**
                     * Saves a new %s entity or updates an existing one.
                     * This method persists the %s object to the database.
                     *
                     * @param %s The %s entity to save or update.
                     * @return The saved or updated %s entity.
                     */
                    public %s save(final %s %s) {
                        return this.%s.save(%s);
                    }

                    /**
                     * Deletes a %s entity by its ID.
                     * This method removes the %s record identified by the given ID from the database.
                     *
                     * @param id The ID of the %s to delete.
                     */
                    public void deleteById(final Long id) {
                        this.%s.deleteById(id);
                    }
                }
                """.formatted(basePackage, camelCaseModuleName,
                basePackage, camelCaseModuleName, pascalCaseModuleName,
                basePackage, camelCaseModuleName, pascalCaseModuleName,
                pascalCaseModuleName, pascalCaseModuleName, pascalCaseModuleName, // Class Javadoc (3 placeholders)
                pascalCaseModuleName, // Class name (1 placeholder)
                pascalCaseModuleName, repositoryVarName, // private final (2 placeholders)
                pascalCaseModuleName, pascalCaseModuleName, // Constructor Javadoc (2 placeholders)
                pascalCaseModuleName, // Constructor Javadoc (1 placeholder)
                repositoryVarName, pascalCaseModuleName, // Constructor Javadoc param (2 placeholders)
                pascalCaseModuleName, pascalCaseModuleName, repositoryVarName, // Constructor method signature (3 placeholders)
                repositoryVarName, repositoryVarName, // this.repo = repo (2 placeholders)
                pascalCaseModuleName, // findAll Javadoc 1 (1 placeholder)
                pascalCaseModuleName, // findAll Javadoc 2 (1 placeholder)
                pascalCaseModuleName, // findAll Javadoc return (1 placeholder)
                pascalCaseModuleName, // findAll method signature (1 placeholder)
                repositoryVarName, // findAll method body (1 placeholder)
                pascalCaseModuleName, // findById Javadoc 1 (1 placeholder)
                pascalCaseModuleName, // findById Javadoc 2 (1 placeholder)
                pascalCaseModuleName, // findById Javadoc param (1 placeholder)
                pascalCaseModuleName, // findById Javadoc return (1 placeholder)
                pascalCaseModuleName, // findById method signature (1 placeholder)
                repositoryVarName, // findById method body (1 placeholder)
                pascalCaseModuleName, // save Javadoc 1 (1 placeholder)
                pascalCaseModuleName, // save Javadoc 2 (1 placeholder)
                camelCaseModuleName, pascalCaseModuleName, // save Javadoc param (2 placeholders)
                pascalCaseModuleName, // save Javadoc return (1 placeholder)
                pascalCaseModuleName, // save method return type (1 placeholder)
                pascalCaseModuleName, camelCaseModuleName, // save method params (2 placeholders)
                repositoryVarName, camelCaseModuleName, // save method body (2 placeholders)
                pascalCaseModuleName, // delete Javadoc 1 (1 placeholder)
                pascalCaseModuleName, // delete Javadoc 2 (1 placeholder)
                pascalCaseModuleName, // delete Javadoc param (1 placeholder)
                repositoryVarName); // delete method body (1 placeholder)
    }
}