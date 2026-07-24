# Spring Framework — Core Concepts

The **Spring Framework** is a comprehensive, lightweight framework for building enterprise Java applications. At its core, it provides **Inversion of Control (IoC)** and **Dependency Injection (DI)** to manage object creation and wiring.

## Table of Contents

- [1. What is Spring Framework?](#1-what-is-spring-framework)
- [2. Spring Architecture](#2-spring-architecture)
- [3. Inversion of Control (IoC)](#3-inversion-of-control-ioc)
- [4. Dependency Injection (DI)](#4-dependency-injection-di)
- [5. Spring IoC Container](#5-spring-ioc-container)
- [6. Spring Beans](#6-spring-beans)
- [7. Bean Scopes](#7-bean-scopes)
- [8. Bean Lifecycle](#8-bean-lifecycle)
- [9. Configuration Approaches](#9-configuration-approaches)
- [10. Component Scanning & Stereotype Annotations](#10-component-scanning--stereotype-annotations)
- [11. Autowiring](#11-autowiring)
- [12. Qualifier & Primary](#12-qualifier--primary)
- [13. Profiles](#13-profiles)
- [14. SpEL (Spring Expression Language)](#14-spel-spring-expression-language)
- [15. Spring AOP (Aspect-Oriented Programming)](#15-spring-aop-aspect-oriented-programming)
- [16. Events in Spring](#16-events-in-spring)
- [17. Spring vs Spring Boot](#17-spring-vs-spring-boot)

---

## 1. What is Spring Framework?

Spring simplifies Java enterprise development by managing the complexity of object creation, configuration, and lifecycle.

### Key Features

- **Lightweight** — minimal overhead, no heavy container required
- **IoC Container** — manages object creation and dependencies
- **AOP Support** — cross-cutting concerns like logging, security, transactions
- **Modular** — use only what you need
- **Integration** — works with JPA, JDBC, JMS, REST, and more
- **Testable** — easy to unit test with DI

---

## 2. Spring Architecture

Spring is organized into modules:

```text
┌─────────────────────────────────────────────┐
│                 Spring Framework             │
├──────────────┬──────────────┬───────────────┤
│   Core       │    Web       │   Data        │
│  Container   │              │   Access      │
├──────────────┼──────────────┼───────────────┤
│ spring-core  │ spring-web   │ spring-jdbc   │
│ spring-beans │ spring-webmvc│ spring-orm    │
│ spring-context│ spring-websocket│ spring-tx │
│ spring-aop   │              │ spring-data   │
├──────────────┴──────────────┴───────────────┤
│              Test (spring-test)              │
└─────────────────────────────────────────────┘
```

### Core Modules

| Module           | Purpose                                      |
|------------------|----------------------------------------------|
| `spring-core`    | Core utilities, IoC foundation               |
| `spring-beans`   | Bean creation, configuration, wiring         |
| `spring-context` | ApplicationContext, events, i18n, scheduling |
| `spring-aop`     | Aspect-Oriented Programming support          |
| `spring-tx`      | Transaction management                       |
| `spring-jdbc`    | JDBC abstraction layer                       |
| `spring-webmvc`  | MVC web framework (DispatcherServlet)        |

---

## 3. Inversion of Control (IoC)

**IoC** means the framework controls object creation and lifecycle instead of the developer doing it manually.

### Without IoC (Tight Coupling)

```java
public class OrderService {
    // Developer creates the dependency manually
    private PaymentService paymentService = new PaymentService();
    private EmailService emailService = new EmailService();

    public void placeOrder() {
        paymentService.processPayment();
        emailService.sendConfirmation();
    }
}
```

**Problems:**
- `OrderService` is tightly coupled to specific implementations
- Hard to test (can't mock dependencies)
- Hard to swap implementations

### With IoC (Loose Coupling)

```java
public class OrderService {
    // Dependencies are injected by the Spring container
    private final PaymentService paymentService;
    private final EmailService emailService;

    public OrderService(PaymentService paymentService, EmailService emailService) {
        this.paymentService = paymentService;
        this.emailService = emailService;
    }

    public void placeOrder() {
        paymentService.processPayment();
        emailService.sendConfirmation();
    }
}
```

**Benefits:**
- Loose coupling — depends on abstractions, not concrete classes
- Easy to test — inject mocks
- Easy to swap implementations

---

## 4. Dependency Injection (DI)

DI is the mechanism that implements IoC — Spring **injects** dependencies into objects rather than objects creating them.

### Types of Dependency Injection

#### 1. Constructor Injection (Recommended)

```java
@Service
public class OrderService {
    private final PaymentService paymentService;
    private final EmailService emailService;

    // Spring injects dependencies via constructor
    @Autowired  // optional in Spring 4.3+ if single constructor
    public OrderService(PaymentService paymentService, EmailService emailService) {
        this.paymentService = paymentService;
        this.emailService = emailService;
    }
}
```

**Why preferred:**
- Fields can be `final` (immutable)
- All dependencies are required — no null fields
- Easy to test (pass dependencies via constructor)
- No reflection needed

#### 2. Setter Injection

```java
@Service
public class OrderService {
    private PaymentService paymentService;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

**Use when:** dependency is optional or can be changed after creation.

#### 3. Field Injection (Not Recommended)

```java
@Service
public class OrderService {
    @Autowired
    private PaymentService paymentService;  // injected directly into field
}
```

**Why avoid:**
- Cannot make fields `final`
- Hard to test without Spring context
- Hides dependencies (not visible in constructor)

### DI Comparison

| Feature            | Constructor      | Setter           | Field            |
|--------------------|------------------|------------------|------------------|
| Immutability       | ✅ `final` fields | ❌               | ❌               |
| Required deps      | ✅ Enforced       | ❌ Optional       | ❌ Hidden         |
| Testability        | ✅ Easy           | ✅ OK             | ❌ Needs reflection|
| Circular deps      | ❌ Fails fast     | ✅ Allows          | ✅ Allows         |
| Recommended        | ✅ Yes            | Sometimes         | ❌ No             |

---

## 5. Spring IoC Container

The IoC container is responsible for creating, configuring, and managing beans.

### Two Types of Containers

| Container              | Interface              | Description                          |
|------------------------|------------------------|--------------------------------------|
| **BeanFactory**        | `BeanFactory`          | Basic container, lazy initialization |
| **ApplicationContext** | `ApplicationContext`    | Advanced, eagerly initializes beans, supports events, i18n, AOP |

```java
// BeanFactory (basic — rarely used directly)
BeanFactory factory = new XmlBeanFactory(new ClassPathResource("beans.xml"));

// ApplicationContext (recommended)
ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
```

### ApplicationContext Implementations

| Implementation                          | Configuration Source          |
|-----------------------------------------|-------------------------------|
| `ClassPathXmlApplicationContext`         | XML files from classpath      |
| `FileSystemXmlApplicationContext`        | XML files from filesystem     |
| `AnnotationConfigApplicationContext`     | Java `@Configuration` classes |
| `AnnotationConfigWebApplicationContext`  | Java config for web apps      |

---

## 6. Spring Beans

A **bean** is any object managed by the Spring IoC container.

### Defining Beans

#### Using Annotations

```java
@Component
public class UserRepository {
    // Spring manages this class as a bean
}

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

#### Using `@Bean` in Configuration Class

```java
@Configuration
public class AppConfig {

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
```

#### Using XML (Legacy)

```xml
<beans>
    <bean id="userRepository" class="com.example.UserRepository"/>
    <bean id="userService" class="com.example.UserService">
        <constructor-arg ref="userRepository"/>
    </bean>
</beans>
```

### When to Use `@Component` vs `@Bean`

| `@Component`                     | `@Bean`                              |
|----------------------------------|--------------------------------------|
| On the class itself              | On a method in `@Configuration`      |
| Auto-detected via component scan | Explicitly defined                   |
| For your own classes              | For third-party library classes      |
| Simpler                          | More control over instantiation      |

---

## 7. Bean Scopes

Scope defines how many instances of a bean the container creates.

| Scope          | Description                                   | Default? |
|----------------|-----------------------------------------------|----------|
| `singleton`    | One instance per Spring container              | ✅ Yes   |
| `prototype`    | New instance every time requested              | No       |
| `request`      | One instance per HTTP request (web only)       | No       |
| `session`      | One instance per HTTP session (web only)       | No       |
| `application`  | One instance per ServletContext (web only)      | No       |

### Examples

```java
@Component
@Scope("singleton")  // default — one shared instance
public class DatabaseConnection { }

@Component
@Scope("prototype")  // new instance every time
public class ReportGenerator { }

// Using constants
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TaskWorker { }
```

### Singleton vs Prototype

```java
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

// Singleton — same instance
UserService s1 = context.getBean(UserService.class);
UserService s2 = context.getBean(UserService.class);
System.out.println(s1 == s2);  // true

// Prototype — different instances
ReportGenerator r1 = context.getBean(ReportGenerator.class);
ReportGenerator r2 = context.getBean(ReportGenerator.class);
System.out.println(r1 == r2);  // false
```

---

## 8. Bean Lifecycle

```text
1. Instantiation        → Spring creates the bean object
2. Populate properties  → Dependencies are injected
3. BeanNameAware        → setBeanName() called
4. BeanFactoryAware     → setBeanFactory() called
5. ApplicationContextAware → setApplicationContext() called
6. @PostConstruct       → Custom initialization method
7. InitializingBean     → afterPropertiesSet() called
8. Custom init-method   → init() called
   ────── Bean is Ready for Use ──────
9. @PreDestroy          → Custom cleanup method
10. DisposableBean      → destroy() called
11. Custom destroy-method → cleanup() called
```

### Lifecycle Callbacks

```java
@Component
public class DatabaseService {
    
    @PostConstruct
    public void init() {
        System.out.println("Bean initialized — connect to database");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Bean destroying — close database connection");
    }
}
```

### Using `@Bean` with Init/Destroy Methods

```java
@Configuration
public class AppConfig {

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    public DatabaseService databaseService() {
        return new DatabaseService();
    }
}

public class DatabaseService {
    public void init() {
        System.out.println("Custom init");
    }

    public void cleanup() {
        System.out.println("Custom destroy");
    }
}
```

### Implementing Interfaces

```java
@Component
public class CacheService implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Cache initialized");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Cache cleared");
    }
}
```

---

## 9. Configuration Approaches

### 1. Java-Based Configuration (Recommended)

```java
@Configuration
@ComponentScan(basePackages = "com.example")
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        ds.setUsername("root");
        ds.setPassword("password");
        return ds;
    }
}

// Bootstrap
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
```

### 2. Annotation-Based Configuration

```java
@Component        // generic bean
@Service          // business logic layer
@Repository       // data access layer
@Controller       // web controller layer

// Spring auto-detects these with @ComponentScan
```

### 3. XML-Based Configuration (Legacy)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userService" class="com.example.UserService">
        <constructor-arg ref="userRepository"/>
    </bean>

    <bean id="userRepository" class="com.example.UserRepository"/>
</beans>
```

---

## 10. Component Scanning & Stereotype Annotations

### Stereotype Annotations

| Annotation    | Layer               | Purpose                              |
|---------------|---------------------|--------------------------------------|
| `@Component`  | Generic             | General-purpose Spring bean          |
| `@Service`    | Service/Business    | Business logic                       |
| `@Repository` | Data Access (DAO)   | Database operations + exception translation |
| `@Controller` | Presentation (Web)  | Handles HTTP requests (MVC)          |
| `@RestController` | REST API        | `@Controller` + `@ResponseBody`      |
| `@Configuration` | Configuration    | Defines `@Bean` methods             |

### Component Scanning

```java
@Configuration
@ComponentScan(basePackages = "com.example")
public class AppConfig { }

// Scans com.example and all sub-packages for @Component, @Service, @Repository, @Controller
```

### Filtering Component Scan

```java
@ComponentScan(
    basePackages = "com.example",
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
    excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Test.*")
)
```

---

## 11. Autowiring

Spring automatically resolves and injects bean dependencies.

### Autowiring Modes

| Mode          | Description                                    |
|---------------|------------------------------------------------|
| `byType`      | Matches by bean type (default for annotations) |
| `byName`      | Matches by bean name                           |
| `constructor`  | Matches constructor parameters by type        |
| `no`           | No autowiring (manual wiring)                 |

### Examples

```java
@Service
public class NotificationService {
    private final EmailService emailService;
    private final SMSService smsService;

    // Autowired by type — Spring finds beans matching these types
    public NotificationService(EmailService emailService, SMSService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void notifyUser(String message) {
        emailService.send(message);
        smsService.send(message);
    }
}
```

### Optional Dependencies

```java
@Autowired(required = false)  // won't fail if bean not found
private AnalyticsService analyticsService;

// Or use Optional
@Autowired
public void setAnalytics(Optional<AnalyticsService> analyticsService) {
    this.analyticsService = analyticsService.orElse(null);
}
```

---

## 12. Qualifier & Primary

When multiple beans of the same type exist, use `@Qualifier` or `@Primary` to resolve ambiguity.

### @Qualifier — Select by Name

```java
public interface PaymentGateway {
    void pay(double amount);
}

@Service("stripe")
public class StripePayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " via Stripe");
    }
}

@Service("paypal")
public class PayPalPayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " via PayPal");
    }
}

@Service
public class OrderService {
    private final PaymentGateway paymentGateway;

    public OrderService(@Qualifier("stripe") PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
}
```

### @Primary — Default Bean

```java
@Service
@Primary  // this bean is used by default when no @Qualifier is specified
public class StripePayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid via Stripe (primary)");
    }
}

@Service
public class PayPalPayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid via PayPal");
    }
}

@Service
public class OrderService {
    // StripePayment is injected (it's @Primary)
    public OrderService(PaymentGateway paymentGateway) {
        paymentGateway.pay(100);
    }
}
```

### @Qualifier vs @Primary

| Feature      | `@Qualifier`                    | `@Primary`                   |
|--------------|---------------------------------|------------------------------|
| Placement    | At injection point              | At bean definition           |
| Specificity  | Explicit — names the exact bean | Default when no qualifier    |
| Priority     | Wins over `@Primary`            | Fallback when no qualifier   |

---

## 13. Profiles

Profiles allow different bean configurations for different environments.

### Defining Profiles

```java
@Configuration
@Profile("dev")
public class DevConfig {
    @Bean
    public DataSource dataSource() {
        // H2 in-memory database for development
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build();
    }
}

@Configuration
@Profile("prod")
public class ProdConfig {
    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://prod-server:3306/proddb");
        return ds;
    }
}

// Profile on a bean
@Service
@Profile("dev")
public class MockEmailService implements EmailService { }

@Service
@Profile("prod")
public class SmtpEmailService implements EmailService { }
```

### Activating Profiles

```java
// 1. In application.properties
spring.profiles.active=dev

// 2. Programmatically
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
context.getEnvironment().setActiveProfiles("dev");
context.register(AppConfig.class);
context.refresh();

// 3. Command line
java -jar app.jar --spring.profiles.active=prod

// 4. JVM argument
-Dspring.profiles.active=dev
```

### Default Profile

```java
@Profile("default")  // active when no profile is explicitly set
public class DefaultConfig { }
```

---

## 14. SpEL (Spring Expression Language)

SpEL allows runtime expressions in annotations and configurations.

```java
@Component
public class AppSettings {

    @Value("${app.name}")              // property value
    private String appName;

    @Value("${app.timeout:5000}")      // with default value
    private int timeout;

    @Value("#{2 * 10}")               // SpEL arithmetic
    private int calculatedValue;

    @Value("#{systemProperties['java.version']}")  // system property
    private String javaVersion;

    @Value("#{T(java.lang.Math).PI}") // static field
    private double pi;

    @Value("#{T(java.lang.Math).random()}")  // static method
    private double randomValue;

    @Value("#{'Hello World'.toUpperCase()}")  // string method
    private String greeting;

    @Value("#{userService.getDefaultName()}")  // bean method
    private String defaultName;
}
```

### Conditional Expressions

```java
@Value("#{${app.env} == 'prod' ? 'Production' : 'Development'}")
private String environment;

@Value("#{${server.port} > 8080 ? 'Custom Port' : 'Default Port'}")
private String portInfo;
```

---

## 15. Spring AOP (Aspect-Oriented Programming)

AOP separates **cross-cutting concerns** (logging, security, transactions) from business logic.

### Key Terminology

| Term          | Description                                                     |
|---------------|-----------------------------------------------------------------|
| **Aspect**    | A module containing cross-cutting logic (e.g., logging)         |
| **Advice**    | The action taken (before, after, around a method)               |
| **Join Point**| A point in execution (e.g., method call)                        |
| **Pointcut**  | An expression that selects join points                          |
| **Weaving**   | Linking aspects to target objects (compile/load/runtime)        |

### Types of Advice

| Advice            | When It Runs                                   |
|-------------------|------------------------------------------------|
| `@Before`         | Before the method executes                     |
| `@After`          | After the method (regardless of outcome)       |
| `@AfterReturning` | After the method returns successfully          |
| `@AfterThrowing`  | After the method throws an exception           |
| `@Around`         | Before and after (wraps the method)            |

### Example

```java
@Aspect
@Component
public class LoggingAspect {

    // Before advice — runs before any method in service package
    @Before("execution(* com.example.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Calling: " + joinPoint.getSignature().getName());
    }

    // After returning — access the return value
    @AfterReturning(pointcut = "execution(* com.example.service.*.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        System.out.println("Method returned: " + result);
    }

    // After throwing — handle exceptions
    @AfterThrowing(pointcut = "execution(* com.example.service.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.out.println("Exception in " + joinPoint.getSignature().getName() + ": " + ex.getMessage());
    }

    // Around advice — full control
    @Around("execution(* com.example.service.*.*(..))")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();  // execute the method
        long elapsed = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature().getName() + " took " + elapsed + "ms");
        return result;
    }
}
```

### Pointcut Expressions

```java
// Any method in service package
@Pointcut("execution(* com.example.service.*.*(..))")
public void serviceMethods() {}

// Methods with specific annotation
@Pointcut("@annotation(com.example.Loggable)")
public void loggableMethods() {}

// All public methods
@Pointcut("execution(public * *(..))")
public void publicMethods() {}

// Methods with specific return type
@Pointcut("execution(String com.example.service.*.*(..))")
public void stringReturningMethods() {}

// Combine pointcuts
@Before("serviceMethods() && publicMethods()")
public void logPublicServiceMethods() { }
```

### Enable AOP

```java
@Configuration
@EnableAspectJAutoProxy
public class AppConfig { }
```

---

## 16. Events in Spring

Spring provides an event-driven mechanism for loose communication between components.

### Define a Custom Event

```java
public class UserRegisteredEvent extends ApplicationEvent {
    private final String username;

    public UserRegisteredEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() { return username; }
}
```

### Publish the Event

```java
@Service
public class UserService {
    private final ApplicationEventPublisher eventPublisher;

    public UserService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void registerUser(String username) {
        // ... registration logic
        eventPublisher.publishEvent(new UserRegisteredEvent(this, username));
    }
}
```

### Listen to the Event

```java
@Component
public class WelcomeEmailListener {

    @EventListener
    public void onUserRegistered(UserRegisteredEvent event) {
        System.out.println("Sending welcome email to: " + event.getUsername());
    }
}

@Component
public class AuditLogListener {

    @EventListener
    public void onUserRegistered(UserRegisteredEvent event) {
        System.out.println("Audit log: User registered — " + event.getUsername());
    }
}
```

### Async Event Handling

```java
@Configuration
@EnableAsync
public class AsyncConfig { }

@Component
public class NotificationListener {

    @Async
    @EventListener
    public void onUserRegistered(UserRegisteredEvent event) {
        // runs in a separate thread
        System.out.println("Async notification for: " + event.getUsername());
    }
}
```

---

## 17. Spring vs Spring Boot

| Feature               | Spring Framework                  | Spring Boot                        |
|-----------------------|------------------------------------|------------------------------------|
| Configuration         | Manual (XML / Java config)         | Auto-configuration                 |
| Server                | External (Tomcat, Jetty)           | Embedded server included           |
| Setup                 | Complex boilerplate                | Quick start with starters          |
| Dependencies          | Manual dependency management       | Starter POMs bundle dependencies   |
| `application.properties` | Not built-in                   | Built-in externalized config       |
| Production-ready      | Manual setup                       | Actuator, health checks built-in   |
| Use case              | Full control, legacy projects      | Rapid development, microservices   |

### Analogy

```text
Spring Framework = Engine, Chassis, Transmission (build the car yourself)
Spring Boot      = Ready-to-drive Car (everything assembled with sensible defaults)
```

### Spring Boot Simplifies Spring

```java
// Spring Framework — requires manual configuration
@Configuration
@EnableWebMvc
@ComponentScan("com.example")
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public ViewResolver viewResolver() { ... }
    @Bean
    public DataSource dataSource() { ... }
    // ... many more beans
}

// Spring Boot — auto-configured
@SpringBootApplication  // = @Configuration + @EnableAutoConfiguration + @ComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
// Just add spring-boot-starter-web and it works!
```

---

## Quick Reference

```text
IoC       → Framework controls object creation
DI        → Dependencies are injected, not created
Bean      → Object managed by Spring container
Scope     → singleton (default), prototype, request, session
Lifecycle → @PostConstruct → use → @PreDestroy
Config    → @Configuration + @Bean or @Component + @ComponentScan
Wiring    → @Autowired (by type), @Qualifier (by name), @Primary (default)
AOP       → @Aspect + @Before/@After/@Around for cross-cutting concerns
Profiles  → @Profile("dev") / @Profile("prod") for environment-specific beans
```
