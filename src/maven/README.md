# Maven in Java

**Apache Maven** is a build automation and project management tool for Java projects. It handles project builds, dependency management, documentation, and reporting using a standard project structure and a `pom.xml` configuration file.

## Table of Contents

- [1. What is Maven?](#1-what-is-maven)
- [2. Why Use Maven?](#2-why-use-maven)
- [3. Installing Maven](#3-installing-maven)
- [4. Maven Project Structure](#4-maven-project-structure)
- [5. POM (Project Object Model)](#5-pom-project-object-model)
- [6. Maven Coordinates (GAV)](#6-maven-coordinates-gav)
- [7. Dependencies](#7-dependencies)
- [8. Dependency Scopes](#8-dependency-scopes)
- [9. Maven Repositories](#9-maven-repositories)
- [10. Maven Build Lifecycle](#10-maven-build-lifecycle)
- [11. Maven Phases](#11-maven-phases)
- [12. Maven Plugins](#12-maven-plugins)
- [13. Maven Profiles](#13-maven-profiles)
- [14. Multi-Module Projects](#14-multi-module-projects)
- [15. Common Maven Commands](#15-common-maven-commands)
- [16. Best Practices](#16-best-practices)

---

## 1. What is Maven?

Maven is a build tool that provides:

- **Standardized project structure** — consistent layout across projects
- **Dependency management** — auto-downloads libraries from repositories
- **Build lifecycle** — predefined phases (compile, test, package, deploy)
- **Plugin architecture** — extensible via plugins

> "Maven" means "accumulator of knowledge" in Yiddish.

---

## 2. Why Use Maven?

| Without Maven                         | With Maven                              |
|---------------------------------------|-----------------------------------------|
| Manually download JAR files           | Auto-downloads from central repository  |
| No standard project structure         | Standardized directory layout           |
| Custom build scripts                  | Predefined build lifecycle              |
| Hard to manage transitive deps        | Handles transitive dependencies         |
| Difficult to share projects           | Portable `pom.xml` defines everything   |

---

## 3. Installing Maven

### Prerequisites

- Java JDK 8+ installed
- `JAVA_HOME` environment variable set

### Steps (macOS)

```bash
# Using Homebrew
brew install maven

# Verify installation
mvn --version
```

### Steps (Manual)

```bash
# 1. Download from https://maven.apache.org/download.cgi
# 2. Extract to a folder (e.g., /opt/maven)
# 3. Set environment variables

export M2_HOME=/opt/maven
export PATH=$M2_HOME/bin:$PATH

# Verify
mvn --version
```

---

## 4. Maven Project Structure

```
my-project/
├── pom.xml                          # Project configuration
├── src/
│   ├── main/
│   │   ├── java/                    # Application source code
│   │   │   └── com/example/App.java
│   │   └── resources/               # Config files (application.properties, etc.)
│   └── test/
│       ├── java/                    # Test source code
│       │   └── com/example/AppTest.java
│       └── resources/               # Test config files
└── target/                          # Build output (compiled classes, JARs)
```

### Creating a New Project

```bash
# Using archetype (project template)
mvn archetype:generate \
    -DgroupId=com.example \
    -DartifactId=my-app \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DinteractiveMode=false
```

---

## 5. POM (Project Object Model)

The `pom.xml` is the core configuration file for a Maven project.

### Minimal pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <!-- Project Coordinates -->
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <!-- Project Info -->
    <name>My Application</name>
    <description>A sample Maven project</description>

    <!-- Properties -->
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <!-- Dependencies -->
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

---

## 6. Maven Coordinates (GAV)

Every Maven artifact is uniquely identified by three coordinates:

| Coordinate    | Description                          | Example           |
|---------------|--------------------------------------|-------------------|
| `groupId`     | Organization/company (reverse domain)| `com.example`     |
| `artifactId`  | Project/module name                  | `my-app`          |
| `version`     | Version number                       | `1.0.0`           |

### Version Conventions

| Version           | Meaning                                  |
|-------------------|------------------------------------------|
| `1.0.0`           | Release version                          |
| `1.0.0-SNAPSHOT`  | Development version (can change)         |
| `1.0.0-RELEASE`   | Stable release                           |
| `1.0.0-RC1`       | Release candidate                        |

---

## 7. Dependencies

Dependencies are external libraries your project needs.

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>3.2.0</version>
    </dependency>

    <!-- MySQL Connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
        <scope>provided</scope>
    </dependency>

    <!-- JUnit 5 -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Transitive Dependencies

If A depends on B, and B depends on C, then A automatically gets C.

```
my-app → spring-boot-starter-web → spring-web → spring-core
```

### Excluding Transitive Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

---

## 8. Dependency Scopes

Scopes control when a dependency is available.

| Scope      | Compile | Test | Runtime | Packaged | Use Case                            |
|------------|---------|------|---------|----------|-------------------------------------|
| `compile`  | ✅      | ✅   | ✅      | ✅       | Default; always available           |
| `provided` | ✅      | ✅   | ❌      | ❌       | Supplied by container (e.g., Servlet API) |
| `runtime`  | ❌      | ✅   | ✅      | ✅       | Not needed at compile time (e.g., JDBC drivers) |
| `test`     | ❌      | ✅   | ❌      | ❌       | Only for tests (e.g., JUnit)        |
| `system`   | ✅      | ✅   | ❌      | ❌       | Like provided but from local path   |
| `import`   | —       | —    | —       | —        | Import BOM dependency management    |

```xml
<!-- Example of each scope -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>  <!-- Tomcat provides this at runtime -->
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
    <scope>runtime</scope>  <!-- Only needed at runtime, not compile -->
</dependency>
```

---

## 9. Maven Repositories

Maven downloads dependencies from repositories.

### Types

| Repository | Location                           | Purpose                        |
|------------|------------------------------------|--------------------------------|
| Local      | `~/.m2/repository`                 | Cache on your machine          |
| Central    | `https://repo.maven.apache.org`    | Default public repository      |
| Remote     | Company/private servers            | Internal/private artifacts     |

### Dependency Resolution Order

```
1. Local Repository (~/.m2/repository)
       ↓ (not found)
2. Central Repository (Maven Central)
       ↓ (not found)
3. Remote Repositories (if configured)
```

### Configuring a Remote Repository

```xml
<repositories>
    <repository>
        <id>company-repo</id>
        <url>https://repo.company.com/maven</url>
    </repository>
</repositories>
```

---

## 10. Maven Build Lifecycle

Maven has three built-in lifecycles:

| Lifecycle  | Purpose                                  |
|------------|------------------------------------------|
| `default`  | Build and deploy the project             |
| `clean`    | Remove previous build output             |
| `site`     | Generate project documentation           |

Each lifecycle consists of **phases** executed in order.

---

## 11. Maven Phases

### Default Lifecycle Phases (in order)

| Phase       | Description                                  |
|-------------|----------------------------------------------|
| `validate`  | Validates project structure and POM          |
| `compile`   | Compiles source code (`src/main/java`)       |
| `test`      | Runs unit tests (`src/test/java`)            |
| `package`   | Packages code into JAR/WAR                   |
| `verify`    | Runs integration tests and checks            |
| `install`   | Installs artifact to local repository        |
| `deploy`    | Deploys artifact to remote repository        |

### Phase Execution

Running a phase executes **all previous phases** in order:

```bash
mvn package
# Executes: validate → compile → test → package
```

### Clean Lifecycle

| Phase         | Description                      |
|---------------|----------------------------------|
| `pre-clean`   | Before cleanup                   |
| `clean`       | Deletes `target/` directory      |
| `post-clean`  | After cleanup                    |

---

## 12. Maven Plugins

Plugins execute tasks during build phases. Every phase is bound to a plugin goal.

### Common Plugins

| Plugin                     | Purpose                           |
|----------------------------|-----------------------------------|
| `maven-compiler-plugin`    | Compiles Java source              |
| `maven-surefire-plugin`    | Runs unit tests                   |
| `maven-jar-plugin`         | Creates JAR files                 |
| `maven-war-plugin`         | Creates WAR files                 |
| `maven-install-plugin`     | Installs to local repo            |
| `maven-deploy-plugin`      | Deploys to remote repo            |
| `maven-shade-plugin`       | Creates fat/uber JARs             |
| `spring-boot-maven-plugin` | Spring Boot packaging/run         |

### Configuring a Plugin

```xml
<build>
    <plugins>
        <!-- Compiler plugin — set Java version -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.11.0</version>
            <configuration>
                <source>17</source>
                <target>17</target>
            </configuration>
        </plugin>

        <!-- Surefire — skip tests -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.1.2</version>
            <configuration>
                <skipTests>false</skipTests>
            </configuration>
        </plugin>

        <!-- Spring Boot plugin -->
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>3.2.0</version>
        </plugin>
    </plugins>
</build>
```

---

## 13. Maven Profiles

Profiles allow different configurations for different environments.

```xml
<profiles>
    <!-- Development profile -->
    <profile>
        <id>dev</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <db.url>jdbc:mysql://localhost:3306/devdb</db.url>
        </properties>
    </profile>

    <!-- Production profile -->
    <profile>
        <id>prod</id>
        <properties>
            <db.url>jdbc:mysql://prod-server:3306/proddb</db.url>
        </properties>
    </profile>

    <!-- Test profile -->
    <profile>
        <id>test</id>
        <properties>
            <db.url>jdbc:h2:mem:testdb</db.url>
        </properties>
    </profile>
</profiles>
```

### Activating a Profile

```bash
# By name
mvn package -Pprod

# By property
mvn package -Denv=production

# By default (activeByDefault)
mvn package
```

---

## 14. Multi-Module Projects

A parent POM manages multiple sub-modules.

### Directory Structure

```
parent-project/
├── pom.xml              (parent POM, packaging = pom)
├── module-core/
│   └── pom.xml
├── module-service/
│   └── pom.xml
└── module-web/
    └── pom.xml
```

### Parent pom.xml

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>parent-project</artifactId>
    <version>1.0.0</version>
    <packaging>pom</packaging>

    <modules>
        <module>module-core</module>
        <module>module-service</module>
        <module>module-web</module>
    </modules>

    <!-- Shared dependency management -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>3.2.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```

### Child Module pom.xml

```xml
<project>
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.example</groupId>
        <artifactId>parent-project</artifactId>
        <version>1.0.0</version>
    </parent>

    <artifactId>module-core</artifactId>

    <dependencies>
        <!-- Version inherited from parent's dependencyManagement -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
    </dependencies>
</project>
```

---

## 15. Common Maven Commands

| Command                          | Description                                |
|----------------------------------|--------------------------------------------|
| `mvn clean`                      | Delete `target/` directory                 |
| `mvn compile`                    | Compile source code                        |
| `mvn test`                       | Run unit tests                             |
| `mvn package`                    | Create JAR/WAR in `target/`                |
| `mvn install`                    | Install artifact to local repo             |
| `mvn deploy`                     | Deploy to remote repository                |
| `mvn clean install`              | Clean + full build + install               |
| `mvn clean package -DskipTests`  | Package without running tests              |
| `mvn dependency:tree`            | Show dependency tree                       |
| `mvn dependency:resolve`         | Resolve and download all dependencies      |
| `mvn versions:display-dependency-updates` | Check for newer dependency versions |
| `mvn help:effective-pom`         | Show the fully resolved POM                |
| `mvn spring-boot:run`            | Run Spring Boot application                |

### Useful Flags

```bash
-DskipTests           # Skip test execution
-Dmaven.test.skip=true # Skip test compilation and execution
-X                    # Debug output
-o                    # Offline mode (use local repo only)
-U                    # Force update snapshots
-pl module-name       # Build specific module
-am                   # Also build dependent modules
```

---

## 16. Best Practices

1. **Use `dependencyManagement`** in parent POM to centralize versions
   ```xml
   <!-- Parent controls versions; children inherit without specifying version -->
   <dependencyManagement>
       <dependencies>
           <dependency>
               <groupId>com.fasterxml.jackson.core</groupId>
               <artifactId>jackson-databind</artifactId>
               <version>2.15.2</version>
           </dependency>
       </dependencies>
   </dependencyManagement>
   ```

2. **Use properties for versions** — easy to update in one place
   ```xml
   <properties>
       <spring.boot.version>3.2.0</spring.boot.version>
       <lombok.version>1.18.30</lombok.version>
   </properties>
   ```

3. **Use appropriate dependency scopes** — don't bundle test libraries in production

4. **Avoid SNAPSHOT versions in production** — use release versions for stable builds

5. **Run `mvn dependency:tree`** to understand and debug dependency conflicts

6. **Use Maven Wrapper (`mvnw`)** — ensures consistent Maven version across team
   ```bash
   mvn wrapper:wrapper -Dmaven=3.9.5
   ./mvnw clean install
   ```

7. **Keep `pom.xml` clean** — remove unused dependencies, use BOMs for framework versions

8. **Use CI/CD integration** — Maven works well with Jenkins, GitHub Actions, GitLab CI

---

## Quick Reference

```bash
# Create project
mvn archetype:generate -DgroupId=com.example -DartifactId=my-app

# Full build
mvn clean install

# Run tests only
mvn test

# Skip tests
mvn clean package -DskipTests

# Check dependencies
mvn dependency:tree

# Run Spring Boot app
mvn spring-boot:run
```
