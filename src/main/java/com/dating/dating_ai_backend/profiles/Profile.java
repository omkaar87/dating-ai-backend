package com.dating.dating_ai_backend.profiles;

public record Profile(
        String id,
        String firstName,
        String lastName,
        int age,
        String ethinicity,
        Gender gender,
        String bio,
        String imageUrl,
        String myersBriggsPersonalityType
) {
}
