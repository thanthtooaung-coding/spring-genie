package com.vinn.springgenie;

/**
 * A helper class to generate the content for a Presentation layer Controller class.
 * This class handles incoming HTTP requests and delegates to the service layer.
 */
public class ControllerClassGenerator {

    /**
     * Generates the content for a Presentation layer Controller class.
     * It includes basic REST endpoints for CRUD operations.
     *
     * @param basePackage          The base package of the application.
     * @param pascalCaseModuleName The module name in PascalCase (e.g., "Product").
     * @return A string containing the Controller class content.
     */
    public static String generate(final String basePackage, final String pascalCaseModuleName) {
        final String camelCaseModuleName = Character.toLowerCase(pascalCaseModuleName.charAt(0)) + pascalCaseModuleName.substring(1);
        final String serviceVarName = camelCaseModuleName + "Service";
        final String pluralModuleName = pascalCaseModuleName + "s";
        final String pluralCamelCaseModuleName = camelCaseModuleName + "s";
        return """
                package %s.%s.controller;

                import %s.%s.entity.%s;
                import %s.%s.service.%sService;
                import org.springframework.http.HttpStatus;
                import org.springframework.http.ResponseEntity;
                import org.springframework.web.bind.annotation.*;

                import java.util.List;

                /**
                 * REST Controller for the %s module.
                 * Handles incoming HTTP requests and interacts with the %sService
                 * to perform operations on %s entities.
                 */
                @RestController
                @RequestMapping("/api/%s") // Base path for this module's API endpoints
                public class %sController {

                    private final %sService %s;

                    /**
                     * Constructs a new %sController with the given %sService.
                     * Spring automatically injects the %sService instance.
                     *
                     * @param %s The %sService to be used by this controller.
                     */
                    public %sController(final %sService %s) {
                        this.%s = %s;
                    }

                    /**
                     * Retrieves all %s entities.
                     *
                     * @return A ResponseEntity containing a list of all %s and HTTP status OK.
                     */
                    @GetMapping
                    public ResponseEntity<List<%s>> getAll%s() {
                        final List<%s> %s = this.%s.findAll();
                        return new ResponseEntity<>(%s, HttpStatus.OK);
                    }

                    /**
                     * Retrieves a single %s entity by its ID.
                     *
                     * @param id The ID of the %s to retrieve.
                     * @return A ResponseEntity containing the %s if found (HTTP status OK),
                     * or HTTP status NOT_FOUND if not found.
                     */
                    @GetMapping("/{id}")
                    public ResponseEntity<%s> get%sById(@PathVariable final  Long id) {
                        return this.%s.findById(id)
                                .map(%s -> new ResponseEntity<>(%s, HttpStatus.OK))
                                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
                    }

                    /**
                     * Creates a new %s entity.
                     *
                     * @param %s The %s object to create, sent in the request body.
                     * @return A ResponseEntity containing the created %s and HTTP status CREATED.
                     */
                    @PostMapping
                    public ResponseEntity<%s> create%s(@RequestBody final  %s %s) {
                        final %s saved%s = this.%s.save(%s);
                        return new ResponseEntity<>(saved%s, HttpStatus.CREATED);
                    }

                    /**
                     * Updates an existing %s entity.
                     *
                     * @param id The ID of the %s to update.
                     * @param %s The updated %s object, sent in the request body.
                     * @return A ResponseEntity containing the updated %s if found (HTTP status OK),
                     * or HTTP status NOT_FOUND if the original %s is not found.
                     */
                    @PutMapping("/{id}")
                    public ResponseEntity<%s> update%s(@PathVariable final Long id, @RequestBody final %s %s) {
                        return this.%s.findById(id)
                                .map(existing%s -> {
                                    existing%s.setName(%s.getName());
                                    existing%s.setDescription(%s.getDescription());
                                    // Set other fields as needed for update
                                    %s updated%s = this.%s.save(existing%s);
                                    return new ResponseEntity<>(updated%s, HttpStatus.OK);
                                })
                                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
                    }

                    /**
                     * Deletes a %s entity by its ID.
                     *
                     * @param id The ID of the %s to delete.
                     * @return A ResponseEntity with HTTP status NO_CONTENT if successful,
                     * or HTTP status NOT_FOUND if the %s does not exist.
                     */
                    @DeleteMapping("/{id}")
                    public ResponseEntity<Void> delete%s(@PathVariable final Long id) {
                        if (%s.findById(id).isPresent()) {
                            this.%s.deleteById(id);
                            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                        } else {
                            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                        }
                    }
                }
                """.formatted(basePackage, camelCaseModuleName, // 1, 2
                basePackage, camelCaseModuleName, pascalCaseModuleName, // 3, 4, 5
                basePackage, camelCaseModuleName, pascalCaseModuleName, // 6, 7, 8
                pascalCaseModuleName, pascalCaseModuleName, pascalCaseModuleName, // 9, 10, 11 (Class Javadoc)
                pluralCamelCaseModuleName, pascalCaseModuleName, // 12, 13 (RequestMapping and Class name)
                pascalCaseModuleName, serviceVarName, // 14, 15 (private final)
                pascalCaseModuleName, pascalCaseModuleName, // 16, 17 (Constructor Javadoc 1)
                pascalCaseModuleName, // 18 (Constructor Javadoc 2)
                serviceVarName, pascalCaseModuleName, // 19, 20 (Constructor Javadoc param)
                pascalCaseModuleName, pascalCaseModuleName, serviceVarName, // 21, 22, 23 (Constructor method signature)
                serviceVarName, serviceVarName, // 24, 25 (this.service = service)
                pascalCaseModuleName, // 26 (getAll Javadoc 1)
                pluralModuleName, // 27 (getAll Javadoc 2)
                pascalCaseModuleName, pluralModuleName, // 28, 29 (getAll method signature)
                pascalCaseModuleName, pluralCamelCaseModuleName, serviceVarName, // 30, 31, 32 (List<Product> products = productService.findAll();)
                pluralCamelCaseModuleName, // 33 (return new ResponseEntity<>(products, HttpStatus.OK);)
                pascalCaseModuleName, // 34 (getById Javadoc 1)
                pascalCaseModuleName, // 35 (getById Javadoc 2)
                pascalCaseModuleName, // 36 (getById Javadoc 3)
                pascalCaseModuleName, pascalCaseModuleName, // 37, 38 (getById method signature)
                serviceVarName, // 39 (getById method body - findById)
                camelCaseModuleName, camelCaseModuleName, // 40, 41 (getById method body - map lambda param and return)
                camelCaseModuleName, // 42 (create Javadoc 1)
                camelCaseModuleName, pascalCaseModuleName, // 43, 44 (create Javadoc param)
                pascalCaseModuleName, // 45 (create Javadoc return)
                pascalCaseModuleName, pascalCaseModuleName, pascalCaseModuleName, camelCaseModuleName, // 46, 47, 48, 49 (create method signature)
                pascalCaseModuleName, camelCaseModuleName, serviceVarName, camelCaseModuleName, // 50, 51, 52, 53 (create method body - save)
                camelCaseModuleName, // 54 (create method body - return)
                pascalCaseModuleName, // 55 (update Javadoc 1)
                camelCaseModuleName, // 56 (update Javadoc 2)
                camelCaseModuleName, pascalCaseModuleName, // 57, 58 (update Javadoc param)
                pascalCaseModuleName, // 59 (update Javadoc return 1)
                pascalCaseModuleName, // 60 (update Javadoc return 2)
                pascalCaseModuleName, pascalCaseModuleName, pascalCaseModuleName, camelCaseModuleName, // 61, 62, 63, 64 (update method signature)
                serviceVarName, // 65 (update method body - findById)
                pascalCaseModuleName, // 66 (update method body - map lambda param)
                pascalCaseModuleName, camelCaseModuleName, // 67, 68 (existing.setName)
                pascalCaseModuleName, camelCaseModuleName, // 69, 70 (existing.setDescription)
                pascalCaseModuleName, pascalCaseModuleName, serviceVarName, pascalCaseModuleName, // 71, 72, 73, 74 (save updated)
                pascalCaseModuleName, // 75 (return updated)
                pascalCaseModuleName, // 76 (delete Javadoc 1)
                pascalCaseModuleName, // 77 (delete Javadoc 2)
                pascalCaseModuleName, // 78 (delete Javadoc 3)
                pascalCaseModuleName, // 79 (delete method signature)
                serviceVarName, // 80 (delete method body - findById)
                serviceVarName); // 81 (delete method body - deleteById)
    }
}