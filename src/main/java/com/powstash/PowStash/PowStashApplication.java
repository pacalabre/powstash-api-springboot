package com.powstash.PowStash;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PowStashApplication {

	static void main(String[] args) {
		SpringApplication.run(PowStashApplication.class, args);
	}


    @Configuration
    public class CorsConfig implements WebMvcConfigurer {
        @Value("${spring.application.allowed-origin}")
        private String allowedOrigin;

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins(allowedOrigin)
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
        }

    }
}
