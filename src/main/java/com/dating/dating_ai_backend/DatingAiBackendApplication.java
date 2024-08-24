package com.dating.dating_ai_backend;

import com.dating.dating_ai_backend.conversations.ConversationRepo;
import com.dating.dating_ai_backend.matches.MatchRepo;
import com.dating.dating_ai_backend.profiles.ProfileCreationService;
import com.dating.dating_ai_backend.profiles.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatingAiBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileCreationService profileCreationService;
	@Autowired
	private ProfileRepo profileRepo;
	@Autowired
	private ConversationRepo conversationRepo;
	@Autowired
	private MatchRepo matchRepo;


	public static void main(String[] args) {
		SpringApplication.run(DatingAiBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//profileCreationService.createProfiles(0);
		clearAllData();
		profileCreationService.saveProfilesToDB();
	}

	private void clearAllData() {
		conversationRepo.deleteAll();
		matchRepo.deleteAll();
		profileRepo.deleteAll();
	}
}
