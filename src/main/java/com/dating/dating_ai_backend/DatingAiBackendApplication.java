package com.dating.dating_ai_backend;

import com.dating.dating_ai_backend.conversations.ChatMessage;
import com.dating.dating_ai_backend.conversations.Conversation;
import com.dating.dating_ai_backend.conversations.ConversationRepo;
import com.dating.dating_ai_backend.profiles.Gender;
import com.dating.dating_ai_backend.profiles.Profile;
import com.dating.dating_ai_backend.profiles.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DatingAiBackendApplication implements CommandLineRunner {


	@Autowired
	private ProfileRepo profileRepo;

	@Autowired
	private ConversationRepo conversationRepo;

	public static void main(String[] args) {
		SpringApplication.run(DatingAiBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		profileRepo.deleteAll();
		conversationRepo.deleteAll();

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

		profile = new Profile(
				"2",
				"Foo",
				"Bar",
				30,
				"Indian",
				Gender.FEMALE,
				"Software programmer",
				"foo.jpg",
				"INTP"
		);

		profileRepo.findAll().forEach(System.out::println);

		Conversation conversation = new Conversation(
				"1",
				profile.id(),
				List.of(
						new ChatMessage("Hello", profile.id(), LocalDateTime.now())
				)
		);

		conversationRepo.save(conversation);
		conversationRepo.findAll().forEach(System.out::println);
	}
}
