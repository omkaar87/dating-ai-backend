package com.dating.dating_ai_backend.conversations;

import com.dating.dating_ai_backend.profiles.Profile;
import com.dating.dating_ai_backend.profiles.ProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class ConversationController {

    private final ConversationRepo conversationRepo;
    private final ProfileRepo profileRepo;
    private final ConversationService conversationService;

    public ConversationController(ConversationRepo conversationRepo, ProfileRepo profileRepo, ConversationService conversationService) {
        this.conversationRepo = conversationRepo;
        this.profileRepo = profileRepo;
        this.conversationService = conversationService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/conversations")
    public Conversation createNewConversation(@RequestBody CreateConversationRequest request) {

        profileRepo.findById(request.profileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find profile id " + request.profileId()));

        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                request.profileId,
                new ArrayList<>()
        );

        conversationRepo.save(conversation);
        return conversation;
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/conversations/{conversationId}")
    public Conversation getConversation(@PathVariable String conversationId) {
        return conversationRepo.findById(conversationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find conversation id " + conversationId));
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/conversations/{conversationId}")
    public Conversation addMessageToConversation(@PathVariable String conversationId,
                                                 @RequestBody ChatMessage chatMessage) {
        Conversation conversation = conversationRepo.findById(conversationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find conversation id " + conversationId));

        String matchProfileId = conversation.profileId();
        Profile profile = profileRepo.findById(matchProfileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find author id " + chatMessage.authorId()));

        Profile user = profileRepo.findById(chatMessage.authorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find author id " + chatMessage.authorId()));



        //TODO : Need to validate that the author of a message happens to be only the profile associated with the message or the user


        ChatMessage messageWithTime = new ChatMessage(
                chatMessage.messageText(),
                chatMessage.authorId(),
                LocalDateTime.now()
        );

        conversation.messages().add(messageWithTime);
        conversationService.generateProfileResponse(conversation, profile, user);
        conversationRepo.save(conversation);
        return conversation;
    }

    public record CreateConversationRequest(String profileId) {
    }
}
