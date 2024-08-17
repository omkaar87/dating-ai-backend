package com.dating.dating_ai_backend.conversations;

import java.time.LocalDateTime;

public record ChatMessage(
        String messageText,
        String authorId,
        LocalDateTime messageTime
) {
}
