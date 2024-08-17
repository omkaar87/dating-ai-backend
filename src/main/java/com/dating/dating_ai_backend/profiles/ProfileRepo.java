package com.dating.dating_ai_backend.profiles;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepo extends MongoRepository<Profile,String> {
}
