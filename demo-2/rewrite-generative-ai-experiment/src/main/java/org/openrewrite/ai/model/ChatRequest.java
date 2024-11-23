package org.openrewrite.ai.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChatRequest {
    @Builder.Default
    private String model = "gpt-4o-mini";
    private List<Message> messages;

}
