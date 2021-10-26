package br.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FiapWebServiceRestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(FiapWebServiceRestfulApplication.class, args);
	}

}
