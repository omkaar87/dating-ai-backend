package com.dating.dating_ai_backend;

import com.dating.dating_ai_backend.profiles.ProfileCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatingAiBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileCreationService profileCreationService;


	public static void main(String[] args) {
		SpringApplication.run(DatingAiBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		profileCreationService.saveProfilesToDB();
	}
}
