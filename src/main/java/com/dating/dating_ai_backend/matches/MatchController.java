package com.dating.dating_ai_backend.matches;

import com.dating.dating_ai_backend.conversations.Conversation;
import com.dating.dating_ai_backend.conversations.ConversationRepo;
import com.dating.dating_ai_backend.profiles.Profile;
import com.dating.dating_ai_backend.profiles.ProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class MatchController {

    private final ConversationRepo conversationRepo;
    private final ProfileRepo profileRepo;
    private final MatchRepo matchRepo;

    public MatchController(ConversationRepo conversationRepo, ProfileRepo profileRepo, MatchRepo matchRepo) {
        this.conversationRepo = conversationRepo;
        this.profileRepo = profileRepo;
        this.matchRepo = matchRepo;
    }

    public record CreateMatchRequest(String profileId){}
    @CrossOrigin(origins = "*")
    @PostMapping("/matches")
    public Match createNewConversation(@RequestBody CreateMatchRequest request) {

        Profile profile = profileRepo.findById(request.profileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find profile id " + request.profileId()));

        //TODO: Make sure there are no existing conversations with this profile already

        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                profile.id(),
                new ArrayList<>()
        );

        conversationRepo.save(conversation);
        Match match = new Match(UUID.randomUUID().toString(), (profile), conversation.id());
        matchRepo.save(match);
        return match;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/matches")
    public List<Match> getAllMatches(){
        return matchRepo.findAll();
    }
}
