### override default properties at runtime
##### default properties are present in classpath:/application.properties and find a way to override properties (specific ones) at runtime

```bash
# option 1 - using --spring.config.location arg
java -jar build/libs/springboot-properties.jar --spring.config.location=classpath:/application.properties,file:./application-override.properties
```
