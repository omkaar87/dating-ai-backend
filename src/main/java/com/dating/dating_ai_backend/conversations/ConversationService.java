package com.dating.dating_ai_backend.conversations;

import com.dating.dating_ai_backend.profiles.Profile;
import org.springframework.ai.chat.messages.*;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationService {

    private OllamaChatModel chatModel;

    public ConversationService(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public Conversation generateProfileResponse(Conversation conversation, Profile profile, Profile user){

        System.out.println("Calling Ollama....");

        String systemMessageStr = "You are a "+profile.age()+" year old "+profile.ethinicity()+" "+profile.gender()
                +" called "+profile.firstName()+" "+profile.lastName()
                +" matched with a "+user.age()+" year old "+user.ethinicity()+" "+user.gender()
                +" called "+user.firstName()+" "+user.lastName()
                +" on Tinder. This is an in-app conversation between you two." +
                " Pretend to be the provided person and respond to the conversation as if writing on Tinder." +
                " Your bio is: "+profile.bio()+" and your Myers Briggs personality type is "+profile.myersBriggsPersonalityType()
                +". Respond in the role of this person only";

        SystemMessage systemMessage = new SystemMessage(systemMessageStr);

        List<AbstractMessage> conversationMessages = conversation.messages().stream().map(message -> {
            if(message.authorId().equals(profile.id())){
                return new AssistantMessage(message.messageText());
            }else{
                return new UserMessage(message.messageText());
            }
        }).toList();

        List<Message> allMessages = new ArrayList<>();
        allMessages.add(systemMessage);
        allMessages.addAll(conversationMessages);


        Prompt prompt = new Prompt(allMessages);
        ChatResponse chatResponse = chatModel.call(prompt);
       /* System.out.println("This is response from Ollama");
        System.out.println(chatResponse.getResult().getOutput().getContent());*/
        conversation.messages().add(new ChatMessage(
                chatResponse.getResult().getOutput().getContent(),
                profile.id(),
                LocalDateTime.now()
        ));
        return conversation;
    }
}
