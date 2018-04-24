### override default properties at runtime
##### default properties are present in classpath:/application.properties and find a way to override properties (specific ones) at runtime

```bash
# option 1 - using --spring.config.location arg ==> this argument will override all default behavious of spring boot's property resolution strategy
java -jar build/libs/springboot-properties.jar --spring.config.location=classpath:/application.properties,file:./application-override.properties
```

```bash
# option 2 - using --spring.config.additional-location arg ==> this argument adds additional properties location which takes precedence over default locations like application.properties but command-line-properties always has higher precedence over spring.config.additional-location.
java -jar build/libs/springboot-properties.jar --spring.config.additional-location=file:./application-override.properties
```


##### Detailed explaination -
```
java -jar build/libs/springboot-properties.jar

2018-04-24 15:08:50,826 TRACE [ main] o.c.s.VerboseEnvironmentPostProcessor    : Number of PropertySources ==> 4
2018-04-24 15:08:50,829 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : Precedence of PropertySources --> systemProperties ==> systemEnvironment ==> random ==> applicationConfig: [classpath:/application.properties]
2018-04-24 15:08:50,829 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : applicationConfig: [classpath:/application.properties](app.full.refresh) ==> false
2018-04-24 15:08:50,830 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : applicationConfig: [classpath:/application.properties](app.fallback.since) ==> 1900-01-01
2018-04-24 15:08:50,834 TRACE [ main] o.c.springbootprops.CmdAppRunner         : Starting up CmdAppRunner !!
2018-04-24 15:08:50,834 DEBUG [ main] o.c.springbootprops.CmdAppRunner         : app.full.refresh ==> false
2018-04-24 15:08:50,835 DEBUG [ main] o.c.springbootprops.CmdAppRunner         : app.fallback.since ==> 1900-01-01
2018-04-24 15:08:50,835 TRACE [ main] o.c.springbootprops.CmdAppRunner         : Shutting down CmdAppRunner !!
2018-04-24 15:08:50,840  INFO [ main] org.coderearth.springbootprops.MainApp   : MainApp shutting down !!
```

```
java -jar build/libs/springboot-properties.jar --spring.config.additional-location=file:./application-override.properties

2018-04-24 15:09:31,479 TRACE [ main] o.c.s.VerboseEnvironmentPostProcessor    : Number of PropertySources ==> 6
2018-04-24 15:09:31,483 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : Precedence of PropertySources --> commandLineArgs ==> systemProperties ==> systemEnvironment ==> random ==> applicationConfig: [file:application-override.properties] ==> applicationConfig: [classpath:/application.properties]
2018-04-24 15:09:31,483 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : commandLineArgs(app.full.refresh) ==> NOT_PRESENT
2018-04-24 15:09:31,483 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : commandLineArgs(app.fallback.since) ==> NOT_PRESENT
2018-04-24 15:09:31,483 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : applicationConfig: [file:application-override.properties](app.full.refresh) ==> true
2018-04-24 15:09:31,483 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : applicationConfig: [file:application-override.properties](app.fallback.since) ==> NOT_PRESENT
2018-04-24 15:09:31,483 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : applicationConfig: [classpath:/application.properties](app.full.refresh) ==> false
2018-04-24 15:09:31,484 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : applicationConfig: [classpath:/application.properties](app.fallback.since) ==> 1900-01-01
2018-04-24 15:09:31,488 TRACE [ main] o.c.springbootprops.CmdAppRunner         : Starting up CmdAppRunner !!
2018-04-24 15:09:31,488 DEBUG [ main] o.c.springbootprops.CmdAppRunner         : app.full.refresh ==> true
2018-04-24 15:09:31,490 DEBUG [ main] o.c.springbootprops.CmdAppRunner         : app.fallback.since ==> 1900-01-01
2018-04-24 15:09:31,491 TRACE [ main] o.c.springbootprops.CmdAppRunner         : Shutting down CmdAppRunner !!
2018-04-24 15:09:31,495  INFO [ main] org.coderearth.springbootprops.MainApp   : MainApp shutting down !!
```


```
java -jar build/libs/springboot-properties.jar --spring.config.additional-location=file:./application-override.properties --app.fallback.since=2000-01-01

2018-04-24 15:09:43,258 TRACE [ main] o.c.s.VerboseEnvironmentPostProcessor    : Number of PropertySources ==> 6
2018-04-24 15:09:43,262 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : Precedence of PropertySources --> commandLineArgs ==> systemProperties ==> systemEnvironment ==> random ==> applicationConfig: [file:application-override.properties] ==> applicationConfig: [classpath:/application.properties]
2018-04-24 15:09:43,262 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : commandLineArgs(app.full.refresh) ==> NOT_PRESENT
2018-04-24 15:09:43,262 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : commandLineArgs(app.fallback.since) ==> 2000-01-01
2018-04-24 15:09:43,262 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : applicationConfig: [file:application-override.properties](app.full.refresh) ==> true
2018-04-24 15:09:43,262 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : applicationConfig: [file:application-override.properties](app.fallback.since) ==> NOT_PRESENT
2018-04-24 15:09:43,262 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : applicationConfig: [classpath:/application.properties](app.full.refresh) ==> false
2018-04-24 15:09:43,262 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : applicationConfig: [classpath:/application.properties](app.fallback.since) ==> 1900-01-01
2018-04-24 15:09:43,267 TRACE [ main] o.c.springbootprops.CmdAppRunner         : Starting up CmdAppRunner !!
2018-04-24 15:09:43,267 DEBUG [ main] o.c.springbootprops.CmdAppRunner         : app.full.refresh ==> true
2018-04-24 15:09:43,268 DEBUG [ main] o.c.springbootprops.CmdAppRunner         : app.fallback.since ==> 2000-01-01
2018-04-24 15:09:43,268 TRACE [ main] o.c.springbootprops.CmdAppRunner         : Shutting down CmdAppRunner !!
2018-04-24 15:09:43,273  INFO [ main] org.coderearth.springbootprops.MainApp   : MainApp shutting down !!
```

```
# only commandline args which overrides everything
java -jar build/libs/springboot-properties.jar --app.fallback.since=2000-01-01

2018-04-24 15:10:35,199 TRACE [ main] o.c.s.VerboseEnvironmentPostProcessor    : Number of PropertySources ==> 5
2018-04-24 15:10:35,202 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : Precedence of PropertySources --> commandLineArgs ==> systemProperties ==> systemEnvironment ==> random ==> applicationConfig: [classpath:/application.properties]
2018-04-24 15:10:35,202 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : commandLineArgs(app.full.refresh) ==> NOT_PRESENT
2018-04-24 15:10:35,202 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : commandLineArgs(app.fallback.since) ==> 2000-01-01
2018-04-24 15:10:35,202 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : applicationConfig: [classpath:/application.properties](app.full.refresh) ==> false
2018-04-24 15:10:35,202 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : applicationConfig: [classpath:/application.properties](app.fallback.since) ==> 1900-01-01
2018-04-24 15:10:35,206 TRACE [ main] o.c.springbootprops.CmdAppRunner         : Starting up CmdAppRunner !!
2018-04-24 15:10:35,206 DEBUG [ main] o.c.springbootprops.CmdAppRunner         : app.full.refresh ==> false
2018-04-24 15:10:35,208 DEBUG [ main] o.c.springbootprops.CmdAppRunner         : app.fallback.since ==> 2000-01-01
2018-04-24 15:10:35,209 TRACE [ main] o.c.springbootprops.CmdAppRunner         : Shutting down CmdAppRunner !!
2018-04-24 15:10:35,212  INFO [ main] org.coderearth.springbootprops.MainApp   : MainApp shutting down !!

java -jar build/libs/springboot-properties.jar --app.fallback.since=2000-01-01 --app.full.refresh=true

2018-04-24 15:10:47,208 TRACE [ main] o.c.s.VerboseEnvironmentPostProcessor    : Number of PropertySources ==> 5
2018-04-24 15:10:47,211 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : Precedence of PropertySources --> commandLineArgs ==> systemProperties ==> systemEnvironment ==> random ==> applicationConfig: [classpath:/application.properties]
2018-04-24 15:10:47,211 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : commandLineArgs(app.full.refresh) ==> true
2018-04-24 15:10:47,211 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : commandLineArgs(app.fallback.since) ==> 2000-01-01
2018-04-24 15:10:47,211 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : applicationConfig: [classpath:/application.properties](app.full.refresh) ==> false
2018-04-24 15:10:47,211 DEBUG [ main] o.c.s.VerboseEnvironmentPostProcessor    : applicationConfig: [classpath:/application.properties](app.fallback.since) ==> 1900-01-01
2018-04-24 15:10:47,215 TRACE [ main] o.c.springbootprops.CmdAppRunner         : Starting up CmdAppRunner !!
2018-04-24 15:10:47,215 DEBUG [ main] o.c.springbootprops.CmdAppRunner         : app.full.refresh ==> true
2018-04-24 15:10:47,217 DEBUG [ main] o.c.springbootprops.CmdAppRunner         : app.fallback.since ==> 2000-01-01
2018-04-24 15:10:47,217 TRACE [ main] o.c.springbootprops.CmdAppRunner         : Shutting down CmdAppRunner !!
2018-04-24 15:10:47,221  INFO [ main] org.coderearth.springbootprops.MainApp   : MainApp shutting down !!
```


