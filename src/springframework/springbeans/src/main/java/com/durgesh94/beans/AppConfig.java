package com.durgesh94.beans;

import com.durgesh94.beans.user.User;
import com.durgesh94.core.notification.PushNotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // @Configuration annotation is used to mark a class as a source of bean definitions for the application context. It indicates that the class contains one or more @Bean methods that will be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.
@ComponentScan // @ComponentScan("com.durgesh94.beans.order") // we can also mention package name, but if we don't mention package name, then spring will scan all the packages in the project
public class AppConfig {

    // Define a bean for User, its from beans module, but we can use it in beans module because beans module has dependency on core module
    @Bean // @Bean annotation is used to indicate that a method produces a bean to be managed by the Spring container. It tells Spring that this method will return an object that should be registered as a bean in the application context.
    public User createUser(){
        return new User("Durgesh", "Tambe", "30");
    }

    // Define a bean for PushNotificationService, its from other module, but we can use it in beans module because beans module has dependency on core module
    @Bean
    public PushNotificationService createPushNotificationService(){
        return new PushNotificationService();
    }
}
