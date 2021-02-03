# Instruction

- File->New->Spring Starter Project->give the project  and package name->Next->Select project dependencies 
  > spring-boot-starter-web, spring-boot-starter-data-jpa, h2database, spring-boot-starter-data-rest and spring-boot-starter-test
- Create sub-packages for controllers , Services, Repositories, Config etc.
  >  eg. kafka.demo.cntrlr, kafka.demo.srvc, kafka.demo.repo
- Create domain/Model classes and there respective Controller and Services etc.
  >  eg. for Department create DepartmentCntrlr.java, DepartmentSrvc.java, DepartmentRepo.java, Department.java
- Create ControllerAdvice for Error Handling
- create Config file for bean configuration
- write CRUD methods 
- add h2DB and logging related properties in application.properties
  >spring.h2.console.enabled=true
spring.h2.console.path=/console
#spring.h2.console.settings.trace=false
#spring.h2.console.settings.web-allow-others=false
spring.datasource.url=jdbc:h2:file:D:/T-Work/h2DB/demo
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=pass
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
logging.level.kafka=INFO
spring.output.ansi.enabled=ALWAYS
- add swagger plugin in into project
-  externalize application.yml configuration and set  set Environment variables  accordingly in run configuration
    >SPRING_CONFIG_LOCATION= {your_directory} , SPRING_CONFIG_NAME= application
- run the application from KafkaDemoApplication.java
- go to [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/) and test the application.
- copy json file from [http://localhost:8080/v2/api-docs](http://localhost:8080/v2/api-docs) to create consumer of this API using RestTemplate.
- create new consumer project and add the json file to resources folder 
- add swagger-codegen-maven-plugin in pom
- clean build the application . It will generate Source code  for consumer
- utilite the generated code to consume API
- Consumer example can be found at https://github.com/8talha8/consumerkafka