package com.filechecker.filechecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FilecheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilecheckerApplication.class, args);
        System.out.println("hello");
	}
}
