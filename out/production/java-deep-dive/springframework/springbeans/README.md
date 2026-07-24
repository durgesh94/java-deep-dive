# Spring IoC Container & Beans

The **IoC (Inversion of Control) Container** is the core of the Spring Framework. It creates objects, wires them together, configures them, and manages their complete lifecycle.

## Table of Contents

- [1. What is IoC?](#1-what-is-ioc)
- [2. What is the IoC Container?](#2-what-is-the-ioc-container)
- [3. BeanFactory vs ApplicationContext](#3-beanfactory-vs-applicationcontext)
- [4. What is a Spring Bean?](#4-what-is-a-spring-bean)
- [5. Ways to Define Beans](#5-ways-to-define-beans)
- [6. Bean Scopes](#6-bean-scopes)
- [7. Bean Lifecycle](#7-bean-lifecycle)
- [8. Dependency Injection (DI)](#8-dependency-injection-di)
- [9. Autowiring](#9-autowiring)
- [10. @Qualifier and @Primary](#10-qualifier-and-primary)
- [11. Lazy Initialization](#11-lazy-initialization)
- [12. Bean Ordering with @Order and @DependsOn](#12-bean-ordering-with-order-and-dependson)
- [13. FactoryBean](#13-factorybean)
- [14. BeanPostProcessor](#14-beanpostprocessor)
- [15. BeanFactoryPostProcessor](#15-beanfactorypostprocessor)
- [16. ApplicationContext Features](#16-applicationcontext-features)
- [17. Practical Example](#17-practical-example)

---

## 1. What is IoC?

**Inversion of Control** means the framework takes responsibility for creating and managing objects instead of the developer doing it manually.

### Without IoC (You Control Everything)

```java
public class OrderService {
    // YOU create the dependencies
    private PaymentService paymentService = new CreditCardPayment();
    private NotificationService notificationService = new EmailNotification();
}
```

**Problems:** tight coupling, hard to test, hard to swap implementations.

### With IoC (Container Controls Everything)

```java
@Service
public class OrderService {
    // CONTAINER creates and injects dependencies
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    public OrderService(PaymentService paymentService, NotificationService notificationService) {
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }
}
```

**Benefits:** loose coupling, easy testing, easy to swap implementations.

### IoC Flow

```text
Without IoC:
  Developer → creates objects → manages lifecycle → wires dependencies

With IoC:
  Developer → defines beans + configuration
  Container → creates objects → manages lifecycle → wires dependencies
```

---

## 2. What is the IoC Container?

The IoC container is the engine that implements IoC. It reads configuration (annotations, Java config, or XML), creates beans, injects dependencies, and manages the bean lifecycle.

```text
┌──────────────────────────────────────────────────┐
│                  IoC Container                    │
│                                                   │
│   Configuration ──► Create Beans ──► Wire Deps    │
│   (annotations,     (instantiate)    (inject)     │
│    @Bean, XML)                                     │
│                                                   │
│   Manage Lifecycle ──► Provide Beans to App       │
│   (init, destroy)      (getBean())                │
└──────────────────────────────────────────────────┘
```

---

## 3. BeanFactory vs ApplicationContext

Spring provides two container interfaces:

### BeanFactory

- Basic container
- **Lazy initialization** — beans created only when requested
- Lightweight, minimal features

```java
BeanFactory factory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
UserService service = factory.getBean(UserService.class); // bean created here
```

### ApplicationContext

- Advanced container (extends BeanFactory)
- **Eager initialization** — singleton beans created at startup
- Supports events, i18n, AOP, resource loading

```java
// From Java configuration
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

// From XML
ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

// For web applications
ApplicationContext context = new AnnotationConfigWebApplicationContext();
```

### Comparison

| Feature                  | BeanFactory         | ApplicationContext      |
|--------------------------|---------------------|-------------------------|
| Bean initialization      | Lazy (on demand)    | Eager (at startup)      |
| Event handling           | ❌                  | ✅                      |
| AOP support              | ❌                  | ✅                      |
| Internationalization     | ❌                  | ✅                      |
| BeanPostProcessor auto   | ❌ Manual           | ✅ Automatic            |
| Annotation support       | Limited             | Full                    |
| Recommended              | Rarely              | ✅ Always               |

### ApplicationContext Implementations

| Implementation                          | Use Case                        |
|-----------------------------------------|---------------------------------|
| `AnnotationConfigApplicationContext`    | Java-based `@Configuration`     |
| `ClassPathXmlApplicationContext`        | XML config from classpath       |
| `FileSystemXmlApplicationContext`       | XML config from file system     |
| `AnnotationConfigWebApplicationContext` | Web apps with Java config       |
| `GenericWebApplicationContext`          | Spring Boot web apps            |

---

## 4. What is a Spring Bean?

A **bean** is any Java object that is created, configured, and managed by the Spring IoC container.

### Not Every Object is a Bean

```java
// This IS a bean — managed by Spring
@Component
public class UserService { }

// This is NOT a bean — created manually
UserService service = new UserService();
```

### Bean Identity

Every bean has:
- **Name/ID** — unique identifier (class name in camelCase by default)
- **Type** — the class of the bean
- **Scope** — how many instances (singleton by default)
- **Dependencies** — other beans it needs

```java
@Component("userService")  // explicit name
public class UserService { }

// Default name: "userService" (class name with lowercase first letter)
@Component
public class UserService { }
```

---

## 5. Ways to Define Beans

### 1. `@Component` and Stereotype Annotations

```java
@Component        // generic bean
public class UtilHelper { }

@Service          // service/business layer
public class OrderService { }

@Repository       // data access layer (+ exception translation)
public class UserRepository { }

@Controller       // web MVC controller
public class UserController { }

@RestController   // REST API controller (@Controller + @ResponseBody)
public class ApiController { }
```

Requires `@ComponentScan` to detect:

```java
@Configuration
@ComponentScan(basePackages = "com.example")
public class AppConfig { }
```

### 2. `@Bean` in `@Configuration` Class

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

    // Custom bean name
    @Bean("emailSender")
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
```

### 3. XML Configuration (Legacy)

```xml
<beans>
    <bean id="userRepository" class="com.example.UserRepository"/>

    <bean id="userService" class="com.example.UserService">
        <constructor-arg ref="userRepository"/>
    </bean>

    <bean id="appConfig" class="com.example.AppConfig">
        <property name="timeout" value="5000"/>
    </bean>
</beans>
```

### When to Use Which?

| Approach        | Use When                                         |
|-----------------|--------------------------------------------------|
| `@Component`    | Your own classes that Spring should auto-detect   |
| `@Bean`         | Third-party classes or complex instantiation      |
| XML             | Legacy projects, external configuration           |

---

## 6. Bean Scopes

Scope defines how many instances of a bean are created.

| Scope          | Instances                                | Use Case                     |
|----------------|------------------------------------------|------------------------------|
| `singleton`    | One per container (default)              | Stateless services, repos    |
| `prototype`    | New instance every request               | Stateful objects             |
| `request`      | One per HTTP request                     | Request-scoped data          |
| `session`      | One per HTTP session                     | User session data            |
| `application`  | One per ServletContext                   | App-wide shared data         |

### Singleton (Default)

```java
@Component
@Scope("singleton")  // default, can be omitted
public class DatabaseConnection {
    public DatabaseConnection() {
        System.out.println("DatabaseConnection created");
        // prints only ONCE
    }
}

// Both references point to the SAME object
DatabaseConnection db1 = context.getBean(DatabaseConnection.class);
DatabaseConnection db2 = context.getBean(DatabaseConnection.class);
System.out.println(db1 == db2);  // true
```

### Prototype

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReportGenerator {
    public ReportGenerator() {
        System.out.println("ReportGenerator created");
        // prints EVERY TIME getBean() is called
    }
}

ReportGenerator r1 = context.getBean(ReportGenerator.class);
ReportGenerator r2 = context.getBean(ReportGenerator.class);
System.out.println(r1 == r2);  // false — different instances
```

### Prototype Inside Singleton Problem

```java
@Component // singleton
public class OrderService {
    @Autowired
    private ReportGenerator reportGenerator; // prototype

    public void generateReport() {
        // PROBLEM: always the same ReportGenerator instance!
        // Because OrderService is singleton, it's injected only once
        reportGenerator.generate();
    }
}
```

#### Solution: Use `ObjectProvider` or `@Lookup`

```java
// Solution 1: ObjectProvider
@Component
public class OrderService {
    private final ObjectProvider<ReportGenerator> reportProvider;

    public OrderService(ObjectProvider<ReportGenerator> reportProvider) {
        this.reportProvider = reportProvider;
    }

    public void generateReport() {
        ReportGenerator report = reportProvider.getObject(); // new instance each time
        report.generate();
    }
}

// Solution 2: @Lookup
@Component
public abstract class OrderService {
    @Lookup
    public abstract ReportGenerator getReportGenerator(); // Spring overrides this

    public void generateReport() {
        ReportGenerator report = getReportGenerator(); // new instance each time
        report.generate();
    }
}
```

---

## 7. Bean Lifecycle

### Complete Lifecycle Flow

```text
1.  Container starts
2.  Bean instantiated (constructor called)
3.  Dependencies injected
4.  BeanNameAware.setBeanName()
5.  BeanFactoryAware.setBeanFactory()
6.  ApplicationContextAware.setApplicationContext()
7.  BeanPostProcessor.postProcessBeforeInitialization()
8.  @PostConstruct method
9.  InitializingBean.afterPropertiesSet()
10. Custom init-method
11. BeanPostProcessor.postProcessAfterInitialization()

     ──── Bean is READY ────

12. @PreDestroy method
13. DisposableBean.destroy()
14. Custom destroy-method
```

### Using Annotations (Recommended)

```java
@Component
public class CacheService {

    @PostConstruct
    public void init() {
        System.out.println("1. Cache initialized — loading data");
    }

    public void doWork() {
        System.out.println("2. Cache in use");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("3. Cache cleared — releasing resources");
    }
}
```

### Using `@Bean` Attributes

```java
@Configuration
public class AppConfig {

    @Bean(initMethod = "initialize", destroyMethod = "shutdown")
    public ConnectionPool connectionPool() {
        return new ConnectionPool();
    }
}

public class ConnectionPool {
    public void initialize() {
        System.out.println("Connection pool started");
    }

    public void shutdown() {
        System.out.println("Connection pool closed");
    }
}
```

### Using Interfaces

```java
@Component
public class AuditService implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Audit service initialized");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Audit service shutting down");
    }
}
```

### Aware Interfaces

```java
@Component
public class MyBean implements BeanNameAware, ApplicationContextAware {

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean name: " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) {
        System.out.println("ApplicationContext available");
    }
}
```

### Lifecycle Callback Priority

If multiple callbacks are defined, they execute in this order:

```text
Init:    @PostConstruct → InitializingBean → custom init-method
Destroy: @PreDestroy → DisposableBean → custom destroy-method
```

---

## 8. Dependency Injection (DI)

### Constructor Injection (Recommended)

```java
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;

    // @Autowired is optional with a single constructor (Spring 4.3+)
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }
}
```

### Setter Injection

```java
@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

### Field Injection (Avoid)

```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;  // hard to test, can't be final
}
```

### Comparison

| Feature           | Constructor       | Setter            | Field             |
|-------------------|-------------------|-------------------|-------------------|
| Immutability      | ✅ `final` fields | ❌                | ❌                |
| Required deps     | ✅ Enforced       | ❌ Optional       | ❌ Hidden         |
| Testability       | ✅ Easy           | ✅ OK             | ❌ Needs Spring   |
| Circular deps     | ❌ Detected early | ✅ Allowed        | ✅ Allowed        |
| Best practice     | ✅ Recommended    | Sometimes         | ❌ Avoid          |

---

## 9. Autowiring

Spring automatically resolves and injects matching beans.

### How Spring Resolves Beans

```text
1. By Type   → find bean matching the parameter type
2. By Name   → if multiple matches, try matching by parameter name
3. @Qualifier → explicit selection
4. @Primary  → default when multiple candidates exist
5. Error     → NoUniqueBeanDefinitionException if unresolved
```

### Example

```java
public interface MessageService {
    void send(String message);
}

@Service
public class EmailService implements MessageService {
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

@Service
public class SmsService implements MessageService {
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}

// Autowiring by type — FAILS (two candidates)
@Service
public class NotificationService {
    // NoUniqueBeanDefinitionException!
    public NotificationService(MessageService messageService) { }
}

// Fix 1: @Qualifier
public NotificationService(@Qualifier("emailService") MessageService messageService) { }

// Fix 2: @Primary on one implementation
@Service
@Primary
public class EmailService implements MessageService { }

// Fix 3: Match by parameter name
public NotificationService(MessageService emailService) { }  // matches "emailService" bean
```

---

## 10. @Qualifier and @Primary

### @Qualifier — Explicit Selection

```java
public interface PaymentGateway {
    void pay(double amount);
}

@Service("razorpay")
public class RazorpayPayment implements PaymentGateway {
    public void pay(double amount) { System.out.println("Razorpay: ₹" + amount); }
}

@Service("stripe")
public class StripePayment implements PaymentGateway {
    public void pay(double amount) { System.out.println("Stripe: $" + amount); }
}

@Service
public class OrderService {
    private final PaymentGateway gateway;

    public OrderService(@Qualifier("razorpay") PaymentGateway gateway) {
        this.gateway = gateway;
    }
}
```

### @Primary — Default Choice

```java
@Service
@Primary  // chosen by default when no @Qualifier is specified
public class RazorpayPayment implements PaymentGateway {
    public void pay(double amount) { System.out.println("Razorpay: ₹" + amount); }
}

@Service
public class StripePayment implements PaymentGateway {
    public void pay(double amount) { System.out.println("Stripe: $" + amount); }
}

@Service
public class OrderService {
    // RazorpayPayment injected (it's @Primary)
    public OrderService(PaymentGateway gateway) {
        gateway.pay(500);
    }
}
```

### Custom Qualifier Annotation

```java
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface India { }

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface International { }

@Service @India
public class RazorpayPayment implements PaymentGateway { }

@Service @International
public class StripePayment implements PaymentGateway { }

@Service
public class OrderService {
    public OrderService(@India PaymentGateway gateway) { }
}
```

---

## 11. Lazy Initialization

By default, singleton beans are created eagerly (at startup). Use `@Lazy` to defer creation until first use.

```java
@Component
@Lazy
public class HeavyService {
    public HeavyService() {
        System.out.println("HeavyService created");
        // expensive initialization
    }
}

// Bean is NOT created at startup
// Created only when first accessed:
HeavyService service = context.getBean(HeavyService.class);
// NOW "HeavyService created" is printed
```

### Lazy Injection

```java
@Service
public class AppService {

    private final HeavyService heavyService;

    // @Lazy on the parameter — proxy injected, real bean created on first method call
    public AppService(@Lazy HeavyService heavyService) {
        this.heavyService = heavyService;
    }
}
```

### Global Lazy Initialization

```properties
# application.properties
spring.main.lazy-initialization=true
```

---

## 12. Bean Ordering with @Order and @DependsOn

### @Order — Execution/Injection Order

```java
public interface StartupTask {
    void execute();
}

@Component
@Order(1)
public class DatabaseMigration implements StartupTask {
    public void execute() { System.out.println("1. Database migrated"); }
}

@Component
@Order(2)
public class CacheWarmup implements StartupTask {
    public void execute() { System.out.println("2. Cache warmed up"); }
}

@Component
@Order(3)
public class HealthCheck implements StartupTask {
    public void execute() { System.out.println("3. Health check passed"); }
}

// Inject all in order
@Service
public class StartupRunner {
    private final List<StartupTask> tasks; // injected in @Order sequence

    public StartupRunner(List<StartupTask> tasks) {
        this.tasks = tasks;
        tasks.forEach(StartupTask::execute);
    }
}
```

### @DependsOn — Bean Creation Order

```java
@Component
@DependsOn("databaseConnection")  // ensure this bean is created first
public class UserRepository {
    public UserRepository() {
        System.out.println("UserRepository created after DatabaseConnection");
    }
}

@Component("databaseConnection")
public class DatabaseConnection {
    public DatabaseConnection() {
        System.out.println("DatabaseConnection created first");
    }
}
```

---

## 13. FactoryBean

A special bean that acts as a **factory** for creating other beans.

```java
public class ToolFactory implements FactoryBean<Tool> {
    private int toolId;

    @Override
    public Tool getObject() throws Exception {
        // complex creation logic
        return new Tool(toolId, "Wrench");
    }

    @Override
    public Class<?> getObjectType() {
        return Tool.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }
}

@Configuration
public class AppConfig {
    @Bean
    public ToolFactory toolFactory() {
        ToolFactory factory = new ToolFactory();
        factory.setToolId(1);
        return factory;
    }
}

// Usage
Tool tool = context.getBean(Tool.class);           // returns the Tool object
ToolFactory factory = context.getBean("&toolFactory", ToolFactory.class); // returns the factory itself
```

---

## 14. BeanPostProcessor

Intercepts bean creation to add custom logic **before** and **after** initialization.

```java
@Component
public class LoggingBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("Before init: " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("After init: " + beanName);
        return bean; // can return a proxy/wrapper here
    }
}
```

### Execution Order

```text
Constructor → DI → postProcessBefore → @PostConstruct → postProcessAfter → Bean Ready
```

### Use Cases

- Creating proxies (how `@Transactional`, AOP work internally)
- Custom annotation processing
- Logging, monitoring bean creation

---

## 15. BeanFactoryPostProcessor

Modifies **bean definitions** before any beans are created. Operates on metadata, not instances.

```java
@Component
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        BeanDefinition definition = beanFactory.getBeanDefinition("userService");
        definition.setScope("prototype"); // change scope before creation
        System.out.println("Modified userService bean definition");
    }
}
```

### BeanPostProcessor vs BeanFactoryPostProcessor

| Feature                  | BeanPostProcessor          | BeanFactoryPostProcessor       |
|--------------------------|----------------------------|---------------------------------|
| Operates on              | Bean instances             | Bean definitions (metadata)     |
| When                     | After bean creation        | Before bean creation            |
| Can modify               | The bean object            | Bean configuration/scope/class  |
| Example                  | Create proxies, AOP        | Property placeholder resolution |

### Built-in BeanFactoryPostProcessors

- `PropertySourcesPlaceholderConfigurer` — resolves `${...}` placeholders
- `ConfigurationClassPostProcessor` — processes `@Configuration` classes

---

## 16. ApplicationContext Features

Beyond basic bean management, `ApplicationContext` provides:

### Environment & Properties

```java
@Component
public class AppService {
    @Autowired
    private Environment environment;

    public void printInfo() {
        String dbUrl = environment.getProperty("db.url");
        String[] profiles = environment.getActiveProfiles();
        System.out.println("DB: " + dbUrl);
        System.out.println("Profiles: " + Arrays.toString(profiles));
    }
}
```

### Resource Loading

```java
@Component
public class FileLoader {
    @Autowired
    private ResourceLoader resourceLoader;

    public void loadFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:data.txt");
        String content = new String(resource.getInputStream().readAllBytes());
        System.out.println(content);
    }
}
```

### Event Publishing

```java
// Publish
@Service
public class UserService {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void register(String name) {
        publisher.publishEvent(new UserRegisteredEvent(this, name));
    }
}

// Listen
@Component
public class WelcomeListener {
    @EventListener
    public void onRegister(UserRegisteredEvent event) {
        System.out.println("Welcome, " + event.getUsername());
    }
}
```

### MessageSource (i18n)

```java
@Autowired
private MessageSource messageSource;

String greeting = messageSource.getMessage("greeting", null, Locale.US);
// messages_en.properties: greeting=Hello!
```

---

## 17. Practical Example

A complete example showing IoC container concepts together:

```java
// --- Interface ---
public interface NotificationService {
    void notify(String message);
}

// --- Implementations ---
@Service("email")
@Primary
public class EmailNotificationService implements NotificationService {
    @Override
    public void notify(String message) {
        System.out.println("Email: " + message);
    }
}

@Service("sms")
public class SmsNotificationService implements NotificationService {
    @Override
    public void notify(String message) {
        System.out.println("SMS: " + message);
    }
}

// --- Service using DI ---
@Service
public class OrderService {
    private final NotificationService primaryNotification;
    private final NotificationService smsNotification;

    public OrderService(
            NotificationService primaryNotification,           // gets @Primary (email)
            @Qualifier("sms") NotificationService smsNotification) {
        this.primaryNotification = primaryNotification;
        this.smsNotification = smsNotification;
    }

    @PostConstruct
    public void init() {
        System.out.println("OrderService ready");
    }

    public void placeOrder(String item) {
        System.out.println("Order placed: " + item);
        primaryNotification.notify("Order confirmed: " + item);
        smsNotification.notify("Order confirmed: " + item);
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("OrderService shutting down");
    }
}

// --- Configuration ---
@Configuration
@ComponentScan(basePackages = "com.example")
public class AppConfig { }

// --- Main ---
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

        OrderService orderService = context.getBean(OrderService.class);
        orderService.placeOrder("Laptop");

        context.close(); // triggers @PreDestroy
    }
}

// Output:
// OrderService ready
// Order placed: Laptop
// Email: Order confirmed: Laptop
// SMS: Order confirmed: Laptop
// OrderService shutting down
```

---

## Quick Reference

```text
IoC Container     → Creates and manages beans
BeanFactory       → Basic container (lazy)
ApplicationContext→ Advanced container (eager, events, AOP, i18n)
Bean              → Object managed by the container
@Component        → Mark class as a bean (auto-detected)
@Bean             → Define bean in @Configuration class
@Scope            → singleton (default) | prototype | request | session
@PostConstruct    → Initialization callback
@PreDestroy       → Destruction callback
@Autowired        → Inject dependency by type
@Qualifier        → Select specific bean by name
@Primary          → Default bean when multiple candidates
@Lazy             → Defer bean creation until first use
@Order            → Control injection/execution order
@DependsOn        → Ensure another bean is created first
BeanPostProcessor → Customize beans after creation
BeanFactoryPostProcessor → Modify bean definitions before creation
```
