package com.dating.dating_ai_backend.conversations;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationRepo extends MongoRepository<Conversation,String> {
}
