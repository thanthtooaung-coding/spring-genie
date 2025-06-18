package com.vinn.springgenie;

/**
 * A helper class to generate the content for the application configuration file
 * (application.properties or application.yml) based on user's database choices.
 */
public class ApplicationConfigGenerator {

    /**
     * Generates the content for the application configuration file.
     *
     * @param configFileType  The chosen config file type ("properties" or "yml").
     * @param databaseType    The chosen database type ("h2", "mysql", "postgresql").
     * @param databaseName    The name of the database.
     * @param databaseDialect The Hibernate dialect (can be empty if not explicitly provided).
     * @param createDatabaseIfNotExist True if the database should be created if it doesn't exist.
     * @param username        The database username (optional).
     * @param password        The database password (optional).
     * @return A string containing the configuration file content.
     */
    public static String generate(String configFileType, String databaseType, String databaseName, String databaseDialect, boolean createDatabaseIfNotExist, String username, String password) {
        StringBuilder configContent = new StringBuilder();

        String createDbSuffix = "";
        if (createDatabaseIfNotExist && (databaseType.equalsIgnoreCase("mysql") || databaseType.equalsIgnoreCase("postgresql"))) {
            createDbSuffix = "&createDatabaseIfNotExist=true";
        }

        if ("properties".equalsIgnoreCase(configFileType)) {
            configContent.append("spring.datasource.url=");
            switch (databaseType.toLowerCase()) {
                case "h2":
                    configContent.append("jdbc:h2:mem:testdb\n");
                    configContent.append("spring.h2.console.enabled=true\n");
                    configContent.append("spring.h2.console.path=/h2-console\n");
                    break;
                case "mysql":
                    configContent.append("jdbc:mysql://localhost:3306/%s?useSSL=false&serverTimezone=UTC%s\n".formatted(databaseName, createDbSuffix));
                    break;
                case "postgresql":
                    configContent.append("jdbc:postgresql://localhost:5432/%s%s\n".formatted(databaseName, createDbSuffix));
                    break;
                default:
                    configContent.append("jdbc:h2:mem:testdb\n");
                    configContent.append("spring.h2.console.enabled=true\n");
                    configContent.append("spring.h2.console.path=/h2-console\n");
                    break;
            }

            configContent.append("spring.datasource.username=");
            if (!username.isEmpty()) {
                configContent.append(username).append("\n");
            } else {
                switch (databaseType.toLowerCase()) {
                    case "mysql":
                        configContent.append("root\n");
                        break;
                    case "postgresql":
                        configContent.append("postgres\n");
                        break;
                    case "h2":
                    default:
                        configContent.append("sa\n");
                        break;
                }
            }

            configContent.append("spring.datasource.password=");
            if (!password.isEmpty()) {
                configContent.append(password).append("\n");
            } else {
                configContent.append("\n");
            }

            configContent.append("spring.jpa.hibernate.ddl-auto=update\n");

            if (!databaseDialect.isEmpty()) {
                configContent.append("spring.jpa.properties.hibernate.dialect=").append(databaseDialect).append("\n");
            } else {
                switch (databaseType.toLowerCase()) {
                    case "mysql":
                        configContent.append("spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect\n");
                        break;
                    case "postgresql":
                        configContent.append("spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect\n");
                        break;
                    case "h2":
                        configContent.append("spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect\n");
                        break;
                }
            }
            configContent.append("spring.jpa.show-sql=true\n");

        } else if ("yml".equalsIgnoreCase(configFileType)) {
            configContent.append("spring:\n");
            configContent.append("  datasource:\n");
            configContent.append("    url: ");
            switch (databaseType.toLowerCase()) {
                case "h2":
                    configContent.append("jdbc:h2:mem:testdb\n");
                    configContent.append("  h2:\n");
                    configContent.append("    console:\n");
                    configContent.append("      enabled: true\n");
                    configContent.append("      path: /h2-console\n");
                    break;
                case "mysql":
                    configContent.append("jdbc:mysql://localhost:3306/%s?useSSL=false&serverTimezone=UTC%s\n".formatted(databaseName, createDbSuffix));
                    break;
                case "postgresql":
                    configContent.append("jdbc:postgresql://localhost:5432/%s%s\n".formatted(databaseName, createDbSuffix));
                    break;
                default:
                    configContent.append("jdbc:h2:mem:testdb\n");
                    configContent.append("  h2:\n");
                    configContent.append("    console:\n");
                    configContent.append("      enabled: true\n");
                    configContent.append("      path: /h2-console\n");
                    break;
            }

            configContent.append("    username: ");
            if (!username.isEmpty()) {
                configContent.append(username).append("\n");
            } else {
                switch (databaseType.toLowerCase()) {
                    case "mysql":
                        configContent.append("root\n");
                        break;
                    case "postgresql":
                        configContent.append("postgres\n");
                        break;
                    case "h2":
                    default:
                        configContent.append("sa\n");
                        break;
                }
            }
            configContent.append("    password: ");
            if (!password.isEmpty()) {
                configContent.append("\"").append(password).append("\"\n");
            } else {
                configContent.append("\"\"\n");
            }

            configContent.append("  jpa:\n");
            configContent.append("    hibernate:\n");
            configContent.append("      ddl-auto: update\n");
            configContent.append("    show-sql: true\n");
            configContent.append("    properties:\n");
            configContent.append("      hibernate:\n");
            configContent.append("        dialect: ");

            if (!databaseDialect.isEmpty()) {
                configContent.append(databaseDialect).append("\n");
            } else {
                switch (databaseType.toLowerCase()) {
                    case "mysql":
                        configContent.append("org.hibernate.dialect.MySQLDialect\n");
                        break;
                    case "postgresql":
                        configContent.append("org.hibernate.dialect.PostgreSQLDialect\n");
                        break;
                    case "h2":
                        configContent.append("org.hibernate.dialect.H2Dialect\n");
                        break;
                    default:
                        configContent.append("\n");
                        break;
                }
            }
        } else {
            System.err.println("Unsupported config file type: " + configFileType + ". Generating empty config file.");
        }

        return configContent.toString();
    }
}
