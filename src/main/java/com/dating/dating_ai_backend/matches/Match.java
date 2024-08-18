package com.dating.dating_ai_backend.matches;

import com.dating.dating_ai_backend.profiles.Profile;

public record Match (String id,
                     Profile profile,
                     String conversationId){
}
