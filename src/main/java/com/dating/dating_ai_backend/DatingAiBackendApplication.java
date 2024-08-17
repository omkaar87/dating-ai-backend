package com.dating.dating_ai_backend;

import com.dating.dating_ai_backend.profiles.Gender;
import com.dating.dating_ai_backend.profiles.Profile;
import com.dating.dating_ai_backend.profiles.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatingAiBackendApplication implements CommandLineRunner {


	@Autowired
	private ProfileRepo profileRepo;

	public static void main(String[] args) {
		SpringApplication.run(DatingAiBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Profile profile = new Profile(
				"1",
				"Omkar",
				"Shivadekar",
				30,
				"Indian",
				Gender.MALE,
				"Software programmer",
				"foo.jpg",
				"INTP"
		);
		profileRepo.save(profile);
		profileRepo.findAll().forEach(System.out::println);
	}
}
