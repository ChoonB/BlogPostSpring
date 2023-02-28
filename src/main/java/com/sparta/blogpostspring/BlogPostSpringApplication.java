package com.sparta.blogpostspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlogPostSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogPostSpringApplication.class, args);
    }

}
