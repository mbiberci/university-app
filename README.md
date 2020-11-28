# University Application
A simple Spring Cloud Application consisting of microservies to manage students, courses and enrollment.

## Technologies
- Spring Boot
- Spring Cloud
  - Eureka Server/Client
  - Config Server/Client
  - Feign Client
- Spring Data
  - Spring Data JPA
  - Spring Data REST
- H2 Database
- OpenAPI (Swagger)

## Microservices (by Order of Startup)
1. Eureka Server (port: 8761)
2. Config Server (port: 8888)
3. Course Service (port: 8802)
4. Student Service (port: 8801)
5. Enrollment Service (port: 8800)

### Eureka Server
Eureka Server runs on port 8761 and provides communication among all the microservices

### Config Server
Config Server runs on port 8888 and serves configuration to Course, Student and Enrollment services. It registers itselfs to Eureka Server and is discovered by other services thru Eureka Server. Ports for Course, Student and Enrollment services are configurable thru Config Server.

### Course Service
Reads configuration from the Config Server at startup. Needs the Eureka Server to discover the Config Server. Provides REST endpoints for Course CRUD operations. Swagger documentation available at /swagger-ui.html

### Student Service
Reads configuration from the Config Server at startup. Needs the Eureka Server to discover the Config Server. Provides REST endpoints for Course CRUD operations. Swagger documentation available at /swagger-ui.html

### Enrollment Service
Reads configuration from the Config Server at startup. Calls endpoints of Course and Student Services thru FeignClients. Needs the Eureka Server to discover the Config Server, the Course Service and the Student Service. Provides REST endpoints for enrolling to a course and dropping a course. Swagger documentation available at /swagger-ui.html
