package de.pius.cookshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Aktiviert die ganze Scheduling-Infrastruktur und füht @Scheduled-Methoden aus
public class CookshareApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookshareApplication.class, args);
		System.out.println("Hello World!");
	}

}
