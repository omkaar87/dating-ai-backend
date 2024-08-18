package com.dating.dating_ai_backend.matches;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepo extends MongoRepository<Match,String> {
}
