package org.openrewrite.ai.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    private String id;
    private String object;
    private Instant created;
    private String model;
    private Usage usage;
    private List<Choice> choices;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Usage {
        private int promptTokens;
        private int completionTokens;
        private int totalTokens;
        private CompletionTokensDetails completionTokensDetails;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class CompletionTokensDetails {
            private int reasoningTokens;
            private int acceptedPredictionTokens;
            private int rejectedPredictionTokens;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice {
        private Message message;
        private Optional<Object> logprobs;
        private String finishReason;
        private int index;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Message {
            private String role;
            private String content;
        }
    }
}
