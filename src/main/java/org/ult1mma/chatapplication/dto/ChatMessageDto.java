package org.ult1mma.chatapplication.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ChatMessageDto {
    private String sender;
    private String content;
    private Instant timestamp;
}
