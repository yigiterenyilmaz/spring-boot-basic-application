Student–School Management API

This project is a simple Spring Boot REST API that manages students and schools with a many-to-one relationship (each student belongs to one school).
It demonstrates clean layering (controller–service–repository) and basic domain modeling.

In addition to standard CRUD operations, the project includes a feature-toggled student transfer mechanism implemented using Spring’s @ConditionalOnProperty.
The transfer feature can be enabled or disabled via application configuration without changing the code.

When the transfer feature is disabled, the API handles the request gracefully by returning a controlled business-level error instead of a server error, using a global exception handling mechanism.

Key Features

Student and School entities with JPA many-to-one relationship

CRUD operations for core entities

Optional student transfer feature controlled via configuration

@ConditionalOnProperty–based feature toggling

Global exception handling with meaningful HTTP status codes

Clean separation of concerns (Controller, Service, Repository)