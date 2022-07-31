package co.com.nisum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class NisumBrianGomezApplication {

	public static void main(String[] args) {
		SpringApplication.run(NisumBrianGomezApplication.class, args);
	}

}
