package com.filechecker.filechecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilecheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilecheckerApplication.class, args);
        System.out.println("hello");
	}

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

}
