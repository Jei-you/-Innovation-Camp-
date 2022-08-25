spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:testdb://localhost/(...)
spring.datasource.username=username
spring.datasource.password=
spring.h2.console.settings.trace=false
spring.h2.console.settings.web-allow-others=true
spring.datasource.initialization-mode=always
spring.sql.init.enabled=true

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.use_sql=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

-- INSERT INTO USER (USER_ID, USERNAME, PASSWORD, NICKNAME, ACTIVATED)
-- VALUES(1,'admin','8c077930-1ac4-44b2-8b2b-75d627d6301d','admin',1)
--
-- INSERT INTO AUTHORITY(AUTHORITY_NAME)  values ('ROLE_USER');
-- INSERT INTO AUTHORITY(AUTHORITY_NAME)  values ('ROLE_ADMIN');
--
-- INSERT INTO USER_AUTHORITY(USER_ID,AUTHORITY_NAME)  values (1, 'ROLE_USER');
-- INSERT INTO USER_AUTHORITY(USER_ID,AUTHORITY_NAME)  values (1, 'ROLE_ADMIN');