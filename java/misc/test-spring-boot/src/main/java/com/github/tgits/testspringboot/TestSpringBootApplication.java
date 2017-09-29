package com.github.tgits.testspringboot;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
@SpringBootApplication
public class TestSpringBootApplication {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

	public static void main(String[] args) {
		SpringApplication.run(TestSpringBootApplication.class, args);
	}
}
