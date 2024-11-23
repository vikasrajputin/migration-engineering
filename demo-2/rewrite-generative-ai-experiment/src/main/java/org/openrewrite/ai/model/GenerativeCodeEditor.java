/*
 * Copyright 2023 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.ai.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.slf4j.Slf4j;
import org.openrewrite.Cursor;
import org.openrewrite.ExecutionContext;
import org.openrewrite.HttpSenderExecutionContextView;
import org.openrewrite.ai.utils.CodeUtils;
import org.openrewrite.ipc.http.HttpSender;
import org.openrewrite.java.JavaTemplate;
import org.openrewrite.java.tree.Statement;
import org.openrewrite.marker.Markup;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

@Slf4j
public class GenerativeCodeEditor {
    private static final ObjectMapper mapper = JsonMapper.builder()
        .constructorDetector(ConstructorDetector.USE_PROPERTIES_BASED)
        .build()
        .registerModule(new ParameterNamesModule())
        .registerModule(new JavaTimeModule())
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private final Supplier<Cursor> cursor;
    private final HttpSender http;
    private final GenerativeCodeExecutionContextView ctx;

    public GenerativeCodeEditor(Supplier<Cursor> cursor, ExecutionContext context) {
        this.cursor = cursor;
        this.ctx = GenerativeCodeExecutionContextView.view(context);
        this.http = HttpSenderExecutionContextView.view(context).getHttpSender();
    }

    public <J2 extends Statement> J2 edit(J2 j, String instruction) {
        String input = j.printTrimmed(cursor.get());
        Message systemMessage = new Message("system",instruction);
        Message userMessage = new Message("user", input);
        ChatRequest chatRequest = ChatRequest.builder().messages(Arrays.asList(systemMessage, userMessage)).build();
        try (HttpSender.Response raw = http
            .post("https://api.openai.com/v1/chat/completions")
            .withHeader("Authorization", "Bearer " + ctx.getOpenapiToken().trim())
            .withContent("application/json", mapper.writeValueAsBytes(chatRequest))
            .send()) {
            log.info("Response code - {}",raw.getCode());
            ChatResponse response = null;
            Map<String,String> errorResponse = new HashMap<>();
            if(raw.getCode() >= 200 && raw.getCode() < 300) {
                response = mapper.readValue(raw.getBodyAsBytes(), ChatResponse.class);
                log.info("Response body - {}",response);
                String extractedCodeSnippet = CodeUtils.extractCodeFromResponse(response.getChoices().get(0).getMessage().getContent());
                return JavaTemplate.builder(extractedCodeSnippet)
                        .contextSensitive()
                        .build()
                        .apply(cursor.get(), j.getCoordinates().replace());
            }else{
                errorResponse = mapper.readValue(raw.getBodyAsBytes(), Map.class);
                return Markup.warn(j, new IllegalStateException("Code edit failed: " + errorResponse));
            }
        } catch (IOException e) {
            return Markup.warn(j, e);
        }
    }
}
